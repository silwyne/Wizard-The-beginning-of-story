package nilian.game.panel;

import nilian.tile.TileManager;

public interface GamePanel {
    int originalTileSize = 16 ;
    int scale = 3 ;
    int tileSize = originalTileSize * scale ;//16 * 3 = 48 x48 tile
    int maxScreenCol = 24 ;
    int maxScreenRow = 14 ;
    int screenWidth = tileSize * maxScreenCol ;//768 pixels
    int screenHeight = tileSize * maxScreenRow ;//576 pixels
    int maxWorldCol = maxScreenCol ;
    int maxWorldRow = maxScreenRow ;

    TileManager getTileManager();
}
