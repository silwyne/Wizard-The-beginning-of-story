package nilian.Player;

import nilian.Player.suit.PlayerSuit;
import nilian.input.KeyHandler;

import java.awt.image.BufferedImage;

public class PlayerUpdater {

    private final PlayerSchema playerSchema;
    private final MovementHandler movementHandler;
    private final KeyHandler keyHandler;
    private final PlayerSuit playerSuit;


    private PlayerOrientation orientation = PlayerOrientation.RIGHT;
    private BufferedImage playerFrameImage;


    // low level unrelated
    // Set Default values
    private final int speed = 2;

    public PlayerUpdater(PlayerSuit playerSuit, PlayerSchema playerSchema, MovementHandler movementHandler, KeyHandler keyHandler) {
        this.playerSuit = playerSuit;
        this.playerSchema = playerSchema;
        this.movementHandler = movementHandler;
        this.keyHandler = keyHandler;
    }

    public boolean update() {
        int ix = playerSchema.getPlayerX();
        int iy = playerSchema.getPlayerY();

        movementHandler.handleJump();
        //Normal Moving process
        if(movementHandler.isJumping) {// if player is on jump !
            playerSchema.setPlayerState(PlayerState.JUMP);
        }
        else if(keyHandler.upPressed) {
            playerSchema.setPlayerState(PlayerState.JUMP);
            movementHandler.startJump();
        }
        if(keyHandler.rightPressed)
        {
            orientation = PlayerOrientation.RIGHT;
            playerSchema.setPlayerState(PlayerState.RUN);
            movementHandler.movePlayer(playerSchema.getPlayerX() + speed, playerSchema.getPlayerY());
        }
        else if(keyHandler.leftPressed)
        {
            orientation = PlayerOrientation.LEFT;
            playerSchema.setPlayerState(PlayerState.RUN_BACK);
            movementHandler.movePlayer(playerSchema.getPlayerX() - speed, playerSchema.getPlayerY());
        }

        else {
            playerSchema.setPlayerState(PlayerState.IDLE);
        }



        //State Image of Player
        playerFrameImage = getPlayerImage(playerSchema.getPlayerState(), playerSuit, orientation);


        // check for move
        if(ix != playerSchema.getPlayerX()
                || iy != playerSchema.getPlayerY()){
            return true ;
        } else {
            return false ;
        }

    }




    /**
     * returns the player image Based on direction and suit!
     * @param direction direction of the player
     * @param suit player suit object which contains player images
     * @return image of the frame for the player
     */
    public static BufferedImage getPlayerImage(PlayerState direction, PlayerSuit suit, PlayerOrientation playerOrientation) {
        if (direction.equals(PlayerState.JUMP)) {
            return suit.getJumpFrame();
        }
        if (direction.equals(PlayerState.IDLE)) {
            if (playerOrientation.equals(PlayerOrientation.LEFT)) {
                return suit.getIdle_LeftFrame();
            } else {
                return suit.getIdle_RightFrame();
            }
        }
        if (direction.equals(PlayerState.RUN)) {
            return suit.getRunFrame();
        }
        if (direction.equals(PlayerState.RUN_BACK)) {
            return suit.getRunBackFrame();
        }
        return null;
    }


    public BufferedImage getPlayerFrameImage() {
        return playerFrameImage;
    }
}
