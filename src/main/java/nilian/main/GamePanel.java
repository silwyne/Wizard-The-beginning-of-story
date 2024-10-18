package nilian.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import nilian.entity.Player ;
import nilian.tile.TileManager;
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
	
	
	
	public GamePanel()
	{
		
		this.setPreferredSize(new Dimension(screenWidth , screenHeight)) ;
		this.setBackground(Color.black) ;
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread()
	{
		gameThread = new Thread(this) ;
		gameThread.start();
	}

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
	public void update()
	{
		player.update();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g) ;
		Graphics2D g2 = (Graphics2D) g ;
		
		//first tiles
		tileM.draw(g2);//--1
		
		//this way player comes above the back ground and then you can see the player and back ground to gether
		
		//then player
		player.draw(g2) ;//--2
		
		g2.dispose() ;
	}
}













