package nilian.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import nilian.main.GamePanel;

/**
 * Manages all the tiles and returns the Map matrix
 */
public class TileManager {
	GamePanel gp ;
	Tile[] tile ;
	int[][] mapTileNum ;

	/**
	 * This handles all the Tiles and the map of the game
	 * @param gp main GamePanel of the game
	 */
	public TileManager (GamePanel gp)
	{
		this.gp = gp ;
		
		mapTileNum = new int[gp.maxworldcol][gp.maxworldrow] ;

		//Loading the  tiles!
		tile = new Tile[3] ;//number  of tiles !
		tile[0] = getTileImage("/tiles/grass.png") ;
		tile[1] = getTileImage("/tiles/wall.png") ;
		tile[2] = getTileImage("/tiles/water.png") ;

		//loading the map
		mapTileNum = loadMap("/maps/world.txt", gp.maxworldcol, gp.maxworldrow) ;
	}

	/**
	 * Loads the map you want and returns a matrix in return!
	 * @param filePath path to map file
	 * @param maxCol max col you want to get read
	 * @param maxRow max col you want to get read
	 * @return matrix of integers as map!
	 */
	public int[][] loadMap (String filePath, int maxCol, int maxRow)
	{
		int[][] resultMatrix = new int[maxCol][maxRow];
		try
		{
			InputStream is = getClass().getResourceAsStream(filePath) ;
			BufferedReader br = new BufferedReader(new InputStreamReader(is)) ;	
			
			int col = 0 ; 
			int row = 0 ;
			while(col < maxCol && row < maxRow)
			{
				String Line = br.readLine();
				while(col < maxCol)
				{
					String numbers[] = Line.split(" ") ;
					int num = Integer.parseInt(numbers[col]) ;
					resultMatrix[col][row] = num ;
					col ++ ;
				}			
				if(col == maxCol)
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
		return resultMatrix ;
	}

	/**
	 * Loads the Tile you want!
	 * @param tilePath path to Tile image!
	 * @return tile object!
	 */
	public Tile getTileImage(String tilePath)
	{
		Tile tile = null;
		try{
			tile = new Tile();
			tile.image = ImageIO.read(getClass().getResourceAsStream(tilePath));
		} catch (IOException e){
			e.printStackTrace();
		}
		return tile ;
	}
	
	public void draw(Graphics2D g2)
	{
		int worldcol = 0;
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
