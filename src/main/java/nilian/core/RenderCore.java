package nilian.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Properties;

import javax.swing.JPanel;

import nilian.gamepanel.GamePanel;
import nilian.online.connector.joiner.GameClient;
import nilian.online.message.ClientMessage;
import nilian.online.message.ClientMessageType;
import nilian.online.render.OnlineRenderer;
import nilian.tile.BackGroundPic;

/**
 * The main Class of the Game
 * contains what ever you see
 */
public class RenderCore extends JPanel implements Runnable
{
    private final OnlineRenderer onlineRenderer;
    private final GamePanel gamePanel;

    public RenderCore(Properties props, GameClient gameClient)
    {
        this.gamePanel = new GamePanel(props, gameClient);
        loadGraphics();
        this.onlineRenderer = gameClient.getOnlineRenderer();
        
        this.setPreferredSize(new Dimension(this.gamePanel.screenWidth, this.gamePanel.screenHeight)) ;
        this.setBackground(Color.black) ;
        this.setDoubleBuffered(true);
        this.addKeyListener(this.gamePanel.getKeyH());
        this.setFocusable(true);


        introduceToServer();
    }

    private void loadGraphics() {
        loadMap();
        loadBackgroundPicture();
    }

    private void loadMap() {
        int[][] gameMap = this.gamePanel.getTileM().loadMap(
            "/maps/world.txt", this.gamePanel.maxWorldCol, this.gamePanel.maxWorldRow);
        this.gamePanel.getTileM().setMap(gameMap);
    }

    private void loadBackgroundPicture() {
        BackGroundPic backgroundPicture = new BackGroundPic(
            "/backgrounds/7.png", this.gamePanel.maxScreenCol, this.gamePanel.maxScreenRow);
        this.gamePanel.setBackGroundPic(backgroundPicture) ;
    }

    /**
     * Initializes the game Thread!
     */
    public void startGameThread()
    {
        this.gamePanel.setGameThread(new Thread(this));
        this.gamePanel.getGameThread().start();
    }

    /**
     * This is the main engine of the Game
     * In each frame of the game this updates the details and more ...
     */
    @Override
    public void run()
    {
        double drawInterval = (double) 1000000000 / this.gamePanel.FPS ;
        double delta = 0 ;
        long lastTime = System.nanoTime();
        long currentTime ;
        while(this.gamePanel.getGameThread() != null)
        {
            currentTime = System.nanoTime() ;
            delta += (currentTime - lastTime) / drawInterval ;
            lastTime = currentTime ;
            if(delta >= 1) {
                update() ;
                this.repaint() ;
                delta -- ;
            }
        }
    }

    private boolean returnedToIdle = true;
    /**
     * Updates All details of the game
     */
    public void update() {
        boolean moved = this.gamePanel.getPlayer().update();
        // send message to server if player moved!
        if(moved) {
            returnedToIdle = false;
            ClientMessage clientMessage = ClientMessage.newBuilder()
                    .setTimestamp(System.currentTimeMillis())
                    .setType(ClientMessageType.CLIENT_MESSAGE_TYPE_UPDATE_PLACE)
                    .setPlayerInfo(this.gamePanel.getPlayer().getPlayerMessage())
                    .build();
            // send a message to server to say the new position
            this.gamePanel.getGameClient().sendMessage(clientMessage);
        }
        // if the player returned to idle let the server now!
        else if(!returnedToIdle) {
            returnedToIdle = true;
            ClientMessage clientMessage = ClientMessage.newBuilder()
                    .setTimestamp(System.currentTimeMillis())
                    .setType(ClientMessageType.CLIENT_MESSAGE_TYPE_UPDATE_PLACE)
                    .setPlayerInfo(this.gamePanel.getPlayer().getPlayerMessage())
                    .build();
            // send a message to server to say the new position
            this.gamePanel.getGameClient().sendMessage(clientMessage);
        }
    }

    /**
     * Paints the new details of the game
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g ;

        // drawing layer by layer
        this.gamePanel.getBackGroundPic().draw(g2);
        this.gamePanel.getTileM().draw(g2);
        this.gamePanel.getPlayer().draw(g2);
        this.onlineRenderer.draw(g2);

        g2.dispose() ;
    }

    private void introduceToServer() {
        ClientMessage clientMessage = ClientMessage.newBuilder()
                .setTimestamp(System.currentTimeMillis())
                .setType(ClientMessageType.CLIENT_MESSAGE_TYPE_INTRODUCE)
                .setPlayerInfo(this.gamePanel.getPlayer().getPlayerMessage())
                .build();

        this.gamePanel.getGameClient().sendMessage(clientMessage);
    }
}