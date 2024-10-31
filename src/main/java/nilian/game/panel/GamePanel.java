package nilian.game.panel;

import nilian.Player.Player;
import nilian.input.KeyHandler;
import nilian.online.connector.joiner.GameClient;
import nilian.tile.BackGroundPic;
import nilian.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

/**
 * Main Game Class which handles rendering
 */
public class GamePanel extends JPanel {

    public int originalTileSize = 16 ;
    public int scale = 3 ;
    public int tileSize = originalTileSize * scale ;//16 * 3 = 48 x48 tile
    public int maxScreenCol = 24 ;
    public int maxScreenRow = 14 ;
    public int screenWidth = tileSize * maxScreenCol ;//768 pixels
    public int screenHeight = tileSize * maxScreenRow ;//576 pixels
    public int maxWorldCol = maxScreenCol ;
    public int maxWorldRow = maxScreenRow ;
    public int FPS = 60 ;

    private BackGroundPic backGroundPic;
    private TileManager tileM = new TileManager(this) ;
    private KeyHandler keyH = new KeyHandler() ;
    private Thread gameThread ;
    private Player player ;
    private final GameClient gameClient;


    public GamePanel(Properties props, GameClient gameClient) {

        this.player = new Player(this , this.keyH, props.getProperty("player.name"));
        this.gameClient = gameClient;
        this.backGroundPic = new BackGroundPic(this, "/backgrounds/7.png");
        this.setPreferredSize(new Dimension(screenWidth , screenHeight)) ;
        this.setBackground(Color.black) ;
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyH);
        this.setFocusable(true);

        // load map
        this.tileM.setMap(tileM.loadMap("/maps/world.txt", maxWorldCol, maxWorldRow));
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

    public GameClient getGameClient() {
        return gameClient;
    }

    public BackGroundPic getBackGroundPic() {
        return backGroundPic;
    }

    public void setBackGroundPic(BackGroundPic backGroundPic) {
        this.backGroundPic = backGroundPic;
    }
}
