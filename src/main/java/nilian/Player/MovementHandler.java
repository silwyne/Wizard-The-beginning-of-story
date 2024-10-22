package nilian.Player;

import nilian.tile.Tile;

public class MovementHandler {


    private final Player player ;

    public MovementHandler(Player player) {
        this.player = player ;
    }


    /**
     * moves the player
     * @param dx destination player x
     * @param dy destination player y
     * @return boolean if the move happened (true) or not (false)!
     */
    public boolean movePlayer(int dx, int dy) {
        boolean moved = false;

        // Check horizontal movement
        if (canMove(dx, player.playerY)) {
            player.playerX = dx;
            moved = true ;
        } else {
            player.playerX = alignToGrid(player.playerX, dx);
        }

        // Check vertical movement
        if (canMove(player.playerX, dy)) {
            player.playerY = dy;
            moved = true ;
        } else {
            player.playerY = alignToGrid(player.playerY, dy);
        }
        return moved ;
    }

    private boolean canMove(int x, int y) {
        int playerWidth = player.gamePanel.tileSize;
        int playerHeight = player.gamePanel.tileSize;
        int pertValue = player.gamePanel.tileSize / 3 ;
        // Check all four corners of the player
        return checkCollision(x, y) &&
                checkCollision(x + playerWidth - pertValue, y) &&
                checkCollision(x, y + playerHeight - pertValue) &&
                checkCollision(x + playerWidth - pertValue, y + playerHeight - pertValue);
    }

    private boolean checkCollision(int x, int y) {
        return !player.gamePanel.tileM.getTile(x, y).collision ;
    }

    private int alignToGrid(int position, int movement) {
        if (movement > 0) {
            return (position / player.gamePanel.tileSize) * player.gamePanel.tileSize;
        } else {
            return ((position / player.gamePanel.tileSize) + 1) * player.gamePanel.tileSize - 1;
        }
    }
}
