package nilian.gamePanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Properties;


import nilian.online.connector.joiner.GameClient;
import nilian.online.message.ClientMessage;
import nilian.online.message.ClientMessageType;
import nilian.online.render.OnlineRenderer;
import nilian.tile.BackGroundPic;

/**
 * The main Class of the Game
 * contains what ever you see
 */
public class OnlineGamePanel extends GamePanel implements Runnable
{
    private final OnlineRenderer onlineRenderer;
    /**
     * This sets the main Settings of the JPanel
     */
    public OnlineGamePanel(Properties props, GameClient gameClient)
    {
        super(props, gameClient);
        loadGraphics();
        this.onlineRenderer = gameClient.getOnlineRenderer();

        // now introduce to server!
        introduceToServer();
    }

    private void loadGraphics() {
        // load map
        super.getTileM().setMap(super.getTileM().loadMap("/maps/world.txt", maxWorldCol, maxWorldRow));
        super.setBackGroundPic(new BackGroundPic(this, "/backgrounds/7.png")) ;
    }

    /**
     * Initializes the game Thread!
     */
    public void startGameThread()
    {
        super.setGameThread(new Thread(this));
        super.getGameThread().start();
    }

    /**
     * This is the main engine of the Game
     * In each frame of the game this updates the details and more ...
     */
    @Override
    public void run()
    {
        double drawInterval = (double) 1000000000 /FPS ;
        double delta = 0 ;
        long lastTime = System.nanoTime();
        long currentTime ;
        while(super.getGameThread() != null)
        {
            currentTime = System.nanoTime() ;
            delta += (currentTime - lastTime) / drawInterval ;
            lastTime = currentTime ;
            if(delta >= 1) {
                update() ;
                repaint() ;
                delta -- ;
            }
        }
    }

    private boolean returnedToIdle = true;
    /**
     * Updates All details of the game
     */
    public void update() {
        boolean moved = super.getPlayer().update();
        // send message to server if player moved!
        if(moved) {
            returnedToIdle = false;
            ClientMessage clientMessage = ClientMessage.newBuilder()
                    .setTimestamp(System.currentTimeMillis())
                    .setType(ClientMessageType.CLIENT_MESSAGE_TYPE_UPDATE_PLACE)
                    .setPlayerInfo(getPlayer().getPlayerMessage())
                    .build();
            // send a message to server to say the new position
            getGameClient().sendMessage(clientMessage);
        }
        // if the player returned to idle let the server now!
        else if(!returnedToIdle) {
            returnedToIdle = true;
            ClientMessage clientMessage = ClientMessage.newBuilder()
                    .setTimestamp(System.currentTimeMillis())
                    .setType(ClientMessageType.CLIENT_MESSAGE_TYPE_UPDATE_PLACE)
                    .setPlayerInfo(getPlayer().getPlayerMessage())
                    .build();
            // send a message to server to say the new position
            getGameClient().sendMessage(clientMessage);
        }
    }

    /**
     * Paints the new details of the game
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g) ;
        Graphics2D g2 = (Graphics2D) g ;

        // background picture
        super.getBackGroundPic().draw(g2);

        //first tiles
        super.getTileM().draw(g2);//--1

        //this way player comes above the background then you can see the player and background together

        //then player
        super.getPlayer().draw(g2) ;//--2

        // and other online stuff
        onlineRenderer.draw(g2);

        g2.dispose() ;
    }

    private void introduceToServer() {
        ClientMessage clientMessage = ClientMessage.newBuilder()
                .setTimestamp(System.currentTimeMillis())
                .setType(ClientMessageType.CLIENT_MESSAGE_TYPE_INTRODUCE)
                .setPlayerInfo(getPlayer().getPlayerMessage())
                .build();
        // send a message to server to say the new position
        getGameClient().sendMessage(clientMessage);
    }
}