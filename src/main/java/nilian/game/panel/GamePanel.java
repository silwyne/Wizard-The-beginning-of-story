package nilian.game.panel;

import nilian.Player.Player;
import nilian.input.KeyHandler;
import nilian.online.connector.joiner.GameClient;
import nilian.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

/**
 * Main Game Class which handles rendering
 */
public class GamePanel extends JPanel implements GameEntities, Runnable {

    private TileManager tileM = new TileManager(this) ;
    private KeyHandler keyH = new KeyHandler() ;
    private Thread gameThread ;
    private Player player ;
    private GameClient gameClient;


    public GamePanel(Properties props, GameClient gameClient) {

        this.player = new Player(this , this.keyH, props.getProperty("player.name"));
        this.gameClient = gameClient;


        this.setPreferredSize(new Dimension(screenWidth , screenHeight)) ;
        this.setBackground(Color.black) ;
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyH);
        this.setFocusable(true);

        // load map
        this.tileM.setMap(tileM.loadMap("/maps/world.txt", maxWorldCol, maxWorldRow));
    }


    @Override
    public void run() {
        // TODO: Override this method when inherited
    }

    public TileManager getTileM() {
        return tileM;
    }

    public void setTileM(TileManager tileM) {
        this.tileM = tileM;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
