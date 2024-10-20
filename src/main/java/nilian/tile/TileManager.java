package nilian.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

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
			BufferedReader br ;
			if(is != null){
				br = new BufferedReader(new InputStreamReader(is)) ;
				int col = 0 ;
				int row = 0 ;
				while(col < maxCol && row < maxRow)
				{
					String Line = br.readLine();
					while(col < maxCol)
					{
						String[] numbers = Line.split(" ") ;
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
			else {
				throw new Exception("BufferedReader object : br is null!");
			}
		}
		catch(Exception e) {
			e.printStackTrace(System.out);
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
			tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(tilePath)));
		} catch (IOException e){
			e.printStackTrace(System.out);
		}
		return tile ;
	}
	
	public void draw(Graphics2D g2)
	{
		int worldCol = 0;
		int worldRow = 0 ;

		while(worldCol < gp.maxworldcol && worldRow < gp.maxworldrow)
		{
			
			int tileNum = mapTileNum[worldCol][worldRow] ;
			int worldX = worldCol * gp.tileSize ;
			int worldY = worldRow * gp.tileSize ;
			int screenX = worldX - gp.player.worldx + gp.player.screenX;
			int screenY = worldY - gp.player.worldy + gp.player.screenY;
			if( worldX + gp.tileSize > gp.player.worldx - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldx + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldy - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldy + gp.player.screenY)
			{
				g2.drawImage(tile[tileNum].image , screenX , screenY , gp.tileSize, gp.tileSize , null);
			}

			worldCol ++ ;
			if (worldCol == gp.maxworldcol)
			{
				worldCol = 0 ;
				worldRow ++ ;
			}
		}
	}
}
