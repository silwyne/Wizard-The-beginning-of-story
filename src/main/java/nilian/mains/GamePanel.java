package nilian.mains;

import nilian.tile.TileManager;

public interface GamePanel {
    public final int originalTileSize = 16 ;
    public final int scale = 3 ;
    public final int tileSize = originalTileSize * scale ;//16 * 3 = 48 x48 tile
    public final int maxScreenCol = 24 ;
    public final int maxScreenRow = 14 ;
    public final int screenWidth = tileSize * maxScreenCol ;//768 pixels
    public final int screenHeight = tileSize * maxScreenRow ;//576 pixels
    public final int maxWorldCol = maxScreenCol ;
    public final int maxWorldRow = maxScreenRow ;

    public TileManager getTileManager();
}
