package nilian.Player;

import nilian.gamePanel.GamePanel;

/**
 * Handles the physics of movement for a player
 */
public class MovementHandler {


    private final PlayerSchema playerSchema;
    private final GamePanel gamePanel;
    private static final double GRAVITY = 0.6;
    private static final double JUMP_FORCE = -10; // Negative because y-axis is inverted in most game coordinate systems
    private static final double MAX_FALL_SPEED = 10;
    public boolean isJumping = false;
    private double verticalVelocity = 0; // Current vertical velocity
    private final int playerHeight;

    /**
     * Makes an instance of MovementHandler Class
     * @param playerSchema the player you want make him move!
     */
    public MovementHandler(PlayerSchema playerSchema, GamePanel gamePanel) {
        this.playerSchema = playerSchema;
        this.gamePanel = gamePanel;
        this.playerHeight = gamePanel.tileSize;
    }


    /**
     * moves the player
     * @param dx destination player x
     * @param dy destination player y
     */
    public void movePlayer(int dx, int dy) {
        // Check horizontal movement
        if (canMove(dx, playerSchema.getPlayerY())) {
            playerSchema.setPlayerX(dx);
        }
        // Check vertical movement
        if (canMove(playerSchema.getPlayerX(), dy)) {
            playerSchema.setPlayerY(dy);
        }
    }

    /**
     * Checks if player can move to the given position
     * @param x x point of position
     * @param y y point of position
     * @return true if it can move to there
     */
    private boolean canMove(int x, int y) {
        int playerWidth = gamePanel.tileSize;
        int playerHeight = gamePanel.tileSize;
        int pertXValue = gamePanel.tileSize / 3;
        int pertYValue = gamePanel.tileSize / 8;
        // Check all four corners of the player
        return checkMoveable(x + pertXValue, y) &&
                checkMoveable(x + pertXValue, y) &&
                checkMoveable(x + pertXValue, y + playerHeight - pertYValue) &&
                checkMoveable(x + playerWidth - pertXValue, y + playerHeight - pertYValue);
    }

    /**
     * Checks if the player can walk on given position
     * @param x x of position
     * @param y y of position
     * @return true if can
     */
    private boolean checkMoveable(int x, int y) {
        return gamePanel.getTileM().getTile(x, y).moveable ;
    }

    /**
     * Handles the physics of jumping
     */
    public void handleJump() {
        if (isJumping) {
            // Apply gravity
            verticalVelocity += GRAVITY;

            // Limit fall speed
            verticalVelocity = Math.min(verticalVelocity, MAX_FALL_SPEED);

            int detectedFloor = findFloor(playerSchema.getPlayerX(), playerSchema.getPlayerY());

            // Calculate new position
            int newY = (int) (playerSchema.getPlayerY() + verticalVelocity);

            if((newY + playerHeight) >= detectedFloor) {
                playerSchema.setPlayerY(detectedFloor - playerHeight);
                isJumping = false;
                verticalVelocity = 0;
            } else {
                // Attempt to move player
                movePlayer(playerSchema.getPlayerX(), newY);
            }
        }
    }

    /**
     * starts the process of jumping
     */
    public void startJump() {
        if (!isJumping) {
            isJumping = true;
            verticalVelocity = JUMP_FORCE;
        }
    }

    /**
     * tries to find the floor
     * @return bullshit
     */
    private int findFloor(int playerX, int playerY) {
        int countDown = 0;
        int playerHeight = gamePanel.tileSize ;
        int y;
        while(true) {
            y = playerY + playerHeight + countDown;
            if(!gamePanel.getTileM().getTile(playerX, y).moveable) {
                return y - 1;
            }
            countDown ++;
        }
    }
    
}
