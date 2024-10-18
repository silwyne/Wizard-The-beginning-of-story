package nilian.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import nilian.main.GamePanel;

public class TileManager {
	GamePanel gp ;
	Tile[] tile ;
	int mapTileNum[] [] ;
	
	public TileManager (GamePanel gp)
	{
		this.gp = gp ;
		
		tile = new Tile[10] ;
		mapTileNum = new int[gp.maxworldcol][gp.maxworldrow] ;
		
		getTileImage() ;
		loadMap("/maps/world.txt") ;
	}
	
	public void loadMap (String filePath)
	{
		try
		{
			InputStream is = getClass().getResourceAsStream(filePath) ;
			BufferedReader br = new BufferedReader(new InputStreamReader(is)) ;	
			
			int col = 0 ; 
			int row = 0 ;
			while(col < gp.maxworldcol && row < gp.maxworldrow)
			{
				String Line = br.readLine();
				while(col < gp.maxworldcol)
				{
					String numbers[] = Line.split(" ") ;
					int num = Integer.parseInt(numbers[col]) ;
					mapTileNum[col][row] = num ;
					col ++ ;
				}			
				if(col == gp.maxworldcol)
				{
					col = 0 ;
					row ++ ;
				}
			}
			br.close() ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void getTileImage()
	{
		try
		{
			tile[0] = new Tile() ;
			tile[0].image  = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")) ;
			
			tile[1] = new Tile() ;
			tile[1].image  = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")) ;

			tile[2] = new Tile() ;
			tile[2].image  = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")) ;

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2)
	{
		int worldcol = 0 ;
		int worldrow = 0 ;

		while(worldcol < gp.maxworldcol && worldrow < gp.maxworldrow) 
		{
			
			int tileNum = mapTileNum[worldcol][worldrow] ;
			int worldx = worldcol * gp.tileSize ;
			int worldy = worldrow * gp.tileSize ;
			int screenx = worldx - gp.player.worldx + gp.player.screenx ;
			int screeny = worldy - gp.player.worldy + gp.player.screeny ;
			if( worldx + gp.tileSize > gp.player.worldx - gp.player.screenx &&
				worldx - gp.tileSize < gp.player.worldx + gp.player.screenx && 
				worldy + gp.tileSize > gp.player.worldy - gp.player.screeny &&
				worldy - gp.tileSize < gp.player.worldy + gp.player.screeny)
			{
				g2.drawImage(tile[tileNum].image , screenx , screeny , gp.tileSize, gp.tileSize , null);
			}

			worldcol ++ ;
			if (worldcol == gp.maxworldcol)
			{
				worldcol = 0 ;
				worldrow ++ ;
			}
		}
	}
}
