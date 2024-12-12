package nilian.Player.suit;

import nilian.Player.PlayerOrientation;

import java.awt.image.BufferedImage;

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
        return playerSuit.idle_rightParts[idleIndex] ;
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
        return playerSuit.idle_leftParts[idleLeftIndex] ;
    }

    public BufferedImage getJumpFrame() {
        roundFrame ++;
        if(roundFrame == framePerUpdate) {
            roundFrame = 0;
            jumpIndex = (jumpIndex + 1) % playerSuit.jumpImages_num;
        }
        return playerSuit.jump_rightParts[jumpIndex] ;
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
        return playerSuit.jump_leftParts[jumpBackIndex] ;
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
            attackIndex = (attackIndex + 1) % playerSuit.attack_1_Images_num;
        }
        return playerSuit.right_attack_1Parts[attackIndex] ;
    }

    private BufferedImage getLeftAttack_1Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % playerSuit.attack_1_Images_num;
        }
        return playerSuit.left_attack_1Parts[attackIndex] ;
    }

    private BufferedImage getRightAttack_2Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % playerSuit.attack_2_Images_num;
        }
        return playerSuit.right_attack_2Parts[attackIndex] ;
    }

    private BufferedImage getLeftAttack_2Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % playerSuit.attack_2_Images_num;
        }
        return playerSuit.left_attack_2Parts[attackIndex] ;
    }

    private BufferedImage getRightAttack_3Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % playerSuit.attack_3_Images_num;
        }
        return playerSuit.right_attack_3Parts[attackIndex] ;
    }

    private BufferedImage getLeftAttack_3Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % playerSuit.attack_3_Images_num;
        }
        return playerSuit.left_attack_3Parts[attackIndex] ;
    }

}
