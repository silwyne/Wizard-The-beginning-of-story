package nilian.game.panel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Properties;

import javax.swing.JPanel;
import nilian.Player.Player;
import nilian.input.KeyHandler;
import nilian.online.connector.joiner.GameClient;
import nilian.tile.TileManager;

/**
 * The main Class of the Game
 * contains what ever you see
 */
public class OnlineGamePanel extends JPanel implements Runnable, GamePanel
{

    private final int FPS = 60 ;

    public TileManager tileM = new TileManager(this) ;
    KeyHandler keyH = new KeyHandler() ;
    Thread gameThread ;
    public final Player player ;


    /**
     * This sets the main Settings of the JPanel
     */
    public OnlineGamePanel(Properties props, GameClient gameClient)
    {
        this.player = new Player(this , keyH, props.getProperty("player.name")) ;

        this.setPreferredSize(new Dimension(screenWidth , screenHeight)) ;
        this.setBackground(Color.black) ;
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    /**
     * Initializes the game Thread!
     */
    public void startGameThread()
    {
        gameThread = new Thread(this) ;
        gameThread.start();
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
        while(gameThread != null)
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

    /**
     * Updates All details of the game
     */
    public void update() {
        player.update();
    }

    /**
     * Paints the new details of the game
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g) ;
        Graphics2D g2 = (Graphics2D) g ;

        //first tiles
        tileM.draw(g2);//--1

        //this way player comes above the background then you can see the player and background together

        //then player
        player.draw(g2) ;//--2

        g2.dispose() ;
    }

    @Override
    public TileManager getTileManager() {
        return tileM;
    }
}