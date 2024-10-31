package nilian.game.panel;

import nilian.Player.Player;
import nilian.input.KeyHandler;
import nilian.tile.TileManager;

import javax.swing.*;

/**
 * Main Game Class which handles rendering
 */
public class GamePanel extends JPanel implements GameEntities, Runnable {

    private TileManager tileM = new TileManager(this) ;
    private KeyHandler keyH = new KeyHandler() ;
    private Thread gameThread ;
    private Player player ;


    @Override
    public void run() {

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
