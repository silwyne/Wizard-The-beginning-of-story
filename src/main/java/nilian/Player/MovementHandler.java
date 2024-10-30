package nilian.Player;

public class MovementHandler {


    private final Player player ;
    private static final double GRAVITY = 0.6;
    private static final double JUMP_FORCE = -10; // Negative because y-axis is inverted in most game coordinate systems
    private static final double MAX_FALL_SPEED = 10;
    public boolean isJumping = false;
    private double verticalVelocity = 0; // Current vertical velocity
    private int initialY; // Store the initial Y position when jump starts

    public MovementHandler(Player player) {
        this.player = player;
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
        }

        // Check vertical movement
        if (canMove(player.playerX, dy)) {
            player.playerY = dy;
            moved = true ;
        }

        return moved ;
    }

    private boolean canMove(int x, int y) {
        int playerWidth = player.offlineGamePanel.tileSize;
        int playerHeight = player.offlineGamePanel.tileSize;
        int pertXValue = player.offlineGamePanel.tileSize / 3;
        int pertYValue = player.offlineGamePanel.tileSize / 8;
        // Check all four corners of the player
        return checkMoveable(x, y) &&
                checkMoveable(x + playerWidth - pertXValue, y) &&
                checkMoveable(x, y + playerHeight - pertYValue) &&
                checkMoveable(x + playerWidth - pertXValue, y + playerHeight - pertYValue);
    }

    private boolean checkMoveable(int x, int y) {
        return player.offlineGamePanel.getTileManager().getTile(x, y).moveable ;
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



}
