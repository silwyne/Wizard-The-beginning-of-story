package nilian.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import nilian.entity.Player ;
import nilian.tile.TileManager;

/**
 * The main Class of the Game
 * contains what ever you see
 */
public class GamePanel extends JPanel implements Runnable 
{
	public final int originalTileSize = 16 ;
	public final int scale = 3 ;
	public final int tileSize = originalTileSize * scale ;//16 * 3 = 48 x48 tile
	public final int maxScreenCol = 16 ;
	public final int maxScreenRow = 12 ;
	public final int screenWidth = tileSize * maxScreenCol ;//768 pixels
	public final int screenHeight = tileSize * maxScreenRow ;//576 pixels
	
	private final int FPS = 60 ;
	
	TileManager tileM = new TileManager(this) ;
	KeyHandler keyH = new KeyHandler() ;	
	Thread gameThread ;
	public Player player = new Player(this , keyH) ;
	
	
	public final int maxworldcol = 50 ;
	public final int maxworldrow = 11 ;
	public final int worldwidth = tileSize * maxworldcol ;
	public final int worldheight = tileSize * maxworldrow ;


	/**
	 * This sets the main Settings of the JPanel
	 */
	public GamePanel()
	{
		
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
		double drawInterval = 1000000000/FPS ;
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
}