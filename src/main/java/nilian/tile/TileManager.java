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
	Tile[] loadTiles;
	int[][] mapTileNum ;

	/**
	 * This handles all the Tiles and the map of the game
	 * @param gp main GamePanel of the game
	 */
	public TileManager (GamePanel gp)
	{
		this.gp = gp ;
		
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow] ;

		//Loading the  tiles!
		loadTiles = new Tile[3] ;//number  of tiles !
		loadTiles[0] = getTileImage("/tiles/grass.png");
		loadTiles[0].setCollision(true);

		loadTiles[1] = getTileImage("/tiles/wall.png") ;
		loadTiles[1].setCollision(false);

		loadTiles[2] = getTileImage("/tiles/water.png");
		loadTiles[2].setCollision(false);

		//loading the map
		mapTileNum = loadMap("/maps/world.txt", gp.maxWorldCol, gp.maxWorldRow) ;
	}

	/**
	 * Loads the map you want and returns a matrix in return!
	 * @param filePath path to map file
	 * @param maxCol max col you want to get read
	 * @param maxRow max col you want to get read
	 * @return matrix of integers as map!
	 */
	public int[][] loadMap(String filePath, int maxCol, int maxRow) {
		int[][] resultMatrix = new int[maxCol][maxRow];
		try (InputStream is = getClass().getResourceAsStream(filePath);
			 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

			if (is == null) {
				throw new IOException("Cannot find file: " + filePath);
			}

			String line;
			int row = 0;
			while ((line = br.readLine()) != null && row < maxRow) {
				String[] chars = line.trim().split("\\s+");
				for (int col = 0; col < maxCol && col < chars.length; col++) {
					resultMatrix[col][row] = Integer.parseInt(chars[col]);
				}
				row++;
			}

			if (row < maxRow) {
				System.out.println("Warning: File has fewer rows than expected. Filled " + row + " out of " + maxRow);
			}
		} catch (IOException e) {
			System.err.println("Error reading map file: " + e.getMessage());
			e.printStackTrace(System.out);
		} catch (NumberFormatException e) {
			System.err.println("Error parsing number in map file: " + e.getMessage());
			e.printStackTrace(System.out);
		}
		return resultMatrix;
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
		int worldRow = 0;

		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			int screenX = worldCol * gp.tileSize;
			int screenY = worldRow * gp.tileSize;

			// Draw all tiles that are within the screen bounds
			if (screenX < gp.screenWidth && screenY < gp.screenHeight) {
				g2.drawImage(loadTiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}

			worldCol++;
			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}

	public Tile getTile(int x, int y){
		// Calculate the tile column and row
		int col = x / gp.tileSize;
		int row = y / gp.tileSize;

		// Check if the calculated column and row are within the valid range
		if (col >= 0 && col < gp.maxScreenCol && row >= 0 && row < gp.maxScreenRow) {
			// Return the tile at the calculated position in the matrix
			return loadTiles[mapTileNum[col][row]];
		} else {
			// Return null if the coordinates are out of bounds
			return null;
		}
	}
}
