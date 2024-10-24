package nilian.Player;

public class MovementHandler {


    private final Player player ;
    private static final double GRAVITY = 0.6;
    private static final double JUMP_FORCE = -10; // Negative because y-axis is inverted in most game coordinate systems
    private static final double MAX_FALL_SPEED = 10;
    private boolean isJumping = false;
    private double verticalVelocity = 0; // Current vertical velocity
    private int initialY; // Store the initial Y position when jump starts

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

    public void handleJump() {
        if (isJumping) {
            // Apply gravity
            verticalVelocity += GRAVITY;

            // Limit fall speed
            verticalVelocity = Math.min(verticalVelocity, MAX_FALL_SPEED);

            // Calculate new position
            int newY = (int) (player.playerY + verticalVelocity);

            // Attempt to move player
            boolean moved = movePlayer(player.playerX, newY);

            if (!moved) {
                // Collision detected
                if (verticalVelocity > 0) {
                    // We hit the ground
                    isJumping = false;
                    verticalVelocity = 0;
                    // Find the floor
                    player.playerY = findFloor(player.playerY, newY);
                } else {
                    // We hit a ceiling
                    verticalVelocity = 0;
                }
            }

            // Check if we've returned to or below the initial ground level
            if (player.playerY >= initialY) {
                player.playerY = initialY;
                isJumping = false;
                verticalVelocity = 0;
            }
        }
    }

    public void startJump() {
        if (!isJumping) {
            isJumping = true;
            verticalVelocity = JUMP_FORCE;
            initialY = player.playerY;
        }
    }

    private int findFloor(int oldY, int newY) {
        // Binary search to find the exact floor position
        while (oldY < newY) {
            int midY = (oldY + newY) / 2;
            if (movePlayer(player.playerX, midY)) {
                oldY = midY + 1;
            } else {
                newY = midY;
            }
        }
        return oldY - 1;
    }

    private int alignToGround(int y) {
        // Align to the top of the tile below the player
        return (y / player.gamePanel.tileSize) * player.gamePanel.tileSize;
    }
}
