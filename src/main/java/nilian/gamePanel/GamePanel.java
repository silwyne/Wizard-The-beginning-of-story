package nilian.gamepanel;

import java.util.Properties;

import nilian.input.KeyHandler;
import nilian.online.connector.joiner.GameClient;
import nilian.player.Player;
import nilian.tile.BackGroundPic;
import nilian.tile.TileManager;

/**
 * Main Game Class which handles rendering
 */
public class GamePanel  {

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
    private TileManager tileM;
    private KeyHandler keyH = new KeyHandler() ;
    private Thread gameThread ;
    private Player player ;
    private final GameClient gameClient;


    public GamePanel(Properties props, GameClient gameClient) {
        this.gameClient = gameClient;
        this.tileM = new TileManager(this);
        this.tileM.loadTiles();
        this.player = new Player(this, props.getProperty("player.name"), props.getProperty("player.suit"));
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
