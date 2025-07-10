package nilian.Player.suit;

import java.awt.image.BufferedImage;

import nilian.Player.PlayerOrientation;

public class PlayerFrameProvider {

    private final PlayerSuit playerSuit;



    public PlayerFrameProvider(String suitName) {
        this.playerSuit = SuitHandler.getSuit(suitName);

        // make sure playerSuit is not null
        if (playerSuit == null) {
            try {
                throw new IllegalAccessException("There is no suit loaded with suit name you provided");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }


    // variables
    private final int framePerUpdate = 4;
    private final int framePerAttackUpdate = 6;

    private int roundFrame = 0;
    private int idleLeftIndex = 0;
    private int jumpBackIndex = 0;
    private int runBackIndex = 0;
    private int idleIndex = 0;
    private int jumpIndex = 0;
    private int runIndex = 0;
    private int attackIndex = 0;

    public BufferedImage getIdle_RightFrame() {
        roundFrame ++;
        if(roundFrame == framePerUpdate) {
            roundFrame = 0;
            idleIndex = (idleIndex + 1) % playerSuit.idleImages_num;
        }
        return playerSuit.idleRightParts[idleIndex] ;
    }

    public BufferedImage getIdle_LeftFrame() {
        roundFrame ++;
        if(roundFrame == framePerUpdate) {
            roundFrame = 0;
            idleLeftIndex = (idleLeftIndex - 1 + playerSuit.idleImages_num) % playerSuit.idleImages_num;
            if (idleLeftIndex < 0) {
                idleLeftIndex = playerSuit.idleImages_num - 1;
            }
        }
        return playerSuit.idleLeftParts[idleLeftIndex] ;
    }

    public BufferedImage getJumpFrame() {
        roundFrame ++;
        if(roundFrame == framePerUpdate) {
            roundFrame = 0;
            jumpIndex = (jumpIndex + 1) % playerSuit.jumpImages_num;
        }
        return playerSuit.jumpRightParts[jumpIndex] ;
    }

    public BufferedImage getJumpBackFrame() {
        roundFrame ++;
        if(roundFrame == framePerUpdate) {
            roundFrame = 0;
            jumpBackIndex = (jumpBackIndex - 1 + playerSuit.jumpImages_num) % playerSuit.jumpImages_num;
            if (jumpBackIndex < 0) {
                jumpBackIndex = playerSuit.jumpImages_num - 2;
            }
            else  if(jumpBackIndex > playerSuit.jumpImages_num) {
                jumpBackIndex = playerSuit.jumpImages_num - 2;
            }
        }
        return playerSuit.jumpLeftParts[jumpBackIndex] ;
    }

    public BufferedImage getRunFrame() {
        roundFrame ++;
        if(roundFrame == framePerUpdate) {
            roundFrame = 0;
            runIndex = (runIndex + 1) % playerSuit.runImages_num;
        }
        return playerSuit.runParts[runIndex] ;
    }

    public BufferedImage getRunBackFrame() {
        roundFrame ++;
        if(roundFrame == framePerUpdate) {
            roundFrame = 0;
            runBackIndex = (runBackIndex - 1 + playerSuit.runImages_num) % playerSuit.runImages_num;
            if (runBackIndex < 0) {
                runBackIndex = playerSuit.runImages_num - 2;
            } else if(runBackIndex > playerSuit.runImages_num) {
                runBackIndex = playerSuit.runImages_num - 2;
            }
        }
        return playerSuit.runBackParts[runBackIndex] ;
    }

    /*
    Attacking frames
     */
    // attack 1
    public BufferedImage getAttack_01Frame(PlayerOrientation playerOrientation, boolean start) {
        if(start) {
            roundFrame = 0;
        }
        if(playerOrientation.equals(PlayerOrientation.LEFT)) {
            return getLeftAttack_1Frame();
        } else {
            return getRightAttack_1Frame();
        }
    }

    // attack 2
    public BufferedImage getAttack_02Frame(PlayerOrientation playerOrientation, boolean start) {
        if(start) {
            roundFrame = 0;
        }
        if(playerOrientation.equals(PlayerOrientation.LEFT)) {
            return getLeftAttack_2Frame();
        } else {
            return getRightAttack_2Frame();
        }
    }

    // attack 3
    public BufferedImage getAttack_03Frame(PlayerOrientation playerOrientation, boolean start) {
        if(start) {
            roundFrame = 0;
        }
        if(playerOrientation.equals(PlayerOrientation.LEFT)) {
            return getLeftAttack_3Frame();
        } else {
            return getRightAttack_3Frame();
        }
    }

    /*
    Rookie attack getters
     */
    private BufferedImage getRightAttack_1Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % playerSuit.attack_1_images_num;
        }
        return playerSuit.rightAttack_1_Parts[attackIndex] ;
    }

    private BufferedImage getLeftAttack_1Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % playerSuit.attack_1_images_num;
        }
        return playerSuit.leftAttack_1_Parts[attackIndex] ;
    }

    private BufferedImage getRightAttack_2Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % playerSuit.attack_2_images_num;
        }
        return playerSuit.rightAttack_2_Parts[attackIndex] ;
    }

    private BufferedImage getLeftAttack_2Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % playerSuit.attack_2_images_num;
        }
        return playerSuit.leftAttack_2_Parts[attackIndex] ;
    }

    private BufferedImage getRightAttack_3Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % playerSuit.attack_3_images_num;
        }
        return playerSuit.rightAttack_3_Parts[attackIndex] ;
    }

    private BufferedImage getLeftAttack_3Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % playerSuit.attack_3_images_num;
        }
        return playerSuit.leftAttack_3_Parts[attackIndex] ;
    }

}
