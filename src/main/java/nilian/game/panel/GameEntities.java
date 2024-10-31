package nilian.game.panel;


public interface GameEntities {
    int originalTileSize = 16 ;
    int scale = 3 ;
    int tileSize = originalTileSize * scale ;//16 * 3 = 48 x48 tile
    int maxScreenCol = 24 ;
    int maxScreenRow = 14 ;
    int screenWidth = tileSize * maxScreenCol ;//768 pixels
    int screenHeight = tileSize * maxScreenRow ;//576 pixels
    int maxWorldCol = maxScreenCol ;
    int maxWorldRow = maxScreenRow ;
    int FPS = 60 ;
}
