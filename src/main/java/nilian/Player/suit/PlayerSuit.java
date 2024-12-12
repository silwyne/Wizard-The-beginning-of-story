package nilian.Player.suit;

import nilian.Player.PlayerOrientation;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PlayerSuit {

    private final String suitName;
    private final String pathToImagePack;

    private BufferedImage idle_right;
    private BufferedImage idle_left;
    private BufferedImage jump_right;
    private BufferedImage jump_left;
    private BufferedImage run;
    private BufferedImage runBack;
    private BufferedImage right_attack_1;
    private BufferedImage left_attack_1;
    private BufferedImage right_attack_2;
    private BufferedImage left_attack_2;
    private BufferedImage right_attack_3;
    private BufferedImage left_attack_3;

    private BufferedImage[] idle_rightParts;
    private BufferedImage[] idle_leftParts;
    private BufferedImage[] jump_rightParts;
    private BufferedImage[] jump_leftParts;
    private BufferedImage[] runParts;
    private BufferedImage[] runBackParts;
    private BufferedImage[] right_attack_1Parts;
    private BufferedImage[] left_attack_1Parts;
    private BufferedImage[] right_attack_2Parts;
    private BufferedImage[] left_attack_2Parts;
    private BufferedImage[] right_attack_3Parts;
    private BufferedImage[] left_attack_3Parts;

    private int idleImages_num;
    private int jumpImages_num;
    private int runImages_num;
    private int attack_1_Images_num;
    private int attack_2_Images_num;
    private int attack_3_Images_num;

    public PlayerSuit(String pathToImagePack, String suitName) {
        this.pathToImagePack = pathToImagePack;
        this.suitName = suitName;
    }


    /**
     * Sets the number of images of each act
     */
    public void setImagesNum(int idleN, int jumpN, int runN, int attack_1, int attack_2, int attack_3) {
        this.idleImages_num = idleN;
        this.jumpImages_num = jumpN;
        this.runImages_num = runN;
        this.attack_1_Images_num = attack_1;
        this.attack_2_Images_num = attack_2;
        this.attack_3_Images_num = attack_3;
    }

    /**
     * reads raw images
     */
    public void loadImages() {
        try {
            idle_right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathToImagePack+"/idle.png"))) ;
            idle_left = mirrorHorizontally(idle_right);

            jump_right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathToImagePack+"/jump.png"))) ;
            jump_left = mirrorHorizontally(jump_right);

            run = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathToImagePack+"/run.png"))) ;
            runBack = mirrorHorizontally(run);

            right_attack_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathToImagePack+"/right_attack_1.png"))) ;;
            left_attack_1 = mirrorHorizontally(right_attack_1);

            right_attack_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathToImagePack+"/right_attack_2.png"))) ;;
            left_attack_2 = mirrorHorizontally(right_attack_2);

            right_attack_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathToImagePack+"/right_attack_3.png"))) ;;
            left_attack_3 = mirrorHorizontally(right_attack_3);
        } catch(IOException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Processes each image into images you want!
     * The final function which gets called is this to make all things done!
     */
    public void loadFrames() {
        /*
        loading moving frames:
        1. idle
        2. run
        3. runback
        4. jump
         */
        int width = idle_right.getWidth();
        int height = idle_right.getHeight();
        int partWidth = 48;
        int remainingWidth = width % partWidth;
        idle_rightParts = new BufferedImage[idleImages_num];
        for (int i = 0; i < idleImages_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == idleImages_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = idle_right.getSubimage(x, y, partWidth, height);
            idle_rightParts[i] = part;
        }

        width = idle_left.getWidth();
        height = idle_left.getHeight();
        partWidth = 48;
        remainingWidth = width % partWidth;
        idle_leftParts = new BufferedImage[idleImages_num];
        for (int i = 0; i < idleImages_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == idleImages_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = idle_left.getSubimage(x, y, partWidth, height);
            idle_leftParts[i] = part;
        }

        width = jump_right.getWidth();
        height = jump_right.getHeight();
        partWidth = 48;
        remainingWidth = width % partWidth;
        jump_rightParts = new BufferedImage[jumpImages_num];
        for (int i = 0; i < jumpImages_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == jumpImages_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = jump_right.getSubimage(x, y, partWidth, height);
            jump_rightParts[i] = part;
        }

        width = jump_left.getWidth();
        height = jump_left.getHeight();
        partWidth = 48;
        remainingWidth = width % partWidth;
        jump_leftParts = new BufferedImage[jumpImages_num];
        for (int i = 0; i < jumpImages_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == jumpImages_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = jump_left.getSubimage(x, y, partWidth, height);
            jump_leftParts[i] = part;
        }

        width = run.getWidth();
        height = run.getHeight();
        partWidth = 48;
        remainingWidth = width % partWidth;
        runParts = new BufferedImage[runImages_num];
        for (int i = 0; i < runImages_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == runImages_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = run.getSubimage(x, y, partWidth, height);
            runParts[i] = part;
        }

        width = runBack.getWidth();
        height = runBack.getHeight();
        partWidth = 48;
        remainingWidth = width % partWidth;
        runBackParts = new BufferedImage[runImages_num];
        for (int i = 0; i < runImages_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == runImages_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = runBack.getSubimage(x, y, partWidth, height);
            runBackParts[i] = part;
        }

        /*
        Loading attack moves
        1. attack 1 : right & left
        2. attack 2 : right & left
        3. attack 3 : right & left
         */

        // attack 1 : right
        width = right_attack_1.getWidth();
        height = right_attack_1.getHeight();
        partWidth = 48;
        remainingWidth = width % partWidth;
        right_attack_1Parts = new BufferedImage[attack_1_Images_num];
        for (int i = 0; i < attack_1_Images_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == attack_1_Images_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = right_attack_1.getSubimage(x, y, partWidth, height);
            right_attack_1Parts[i] = part;
        }

        // attack 1 : left
        width = left_attack_1.getWidth();
        height = left_attack_1.getHeight();
        partWidth = 48;
        remainingWidth = width % partWidth;
        left_attack_1Parts = new BufferedImage[attack_1_Images_num];
        for (int i = 0; i < attack_1_Images_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == attack_1_Images_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = left_attack_1.getSubimage(x, y, partWidth, height);
            left_attack_1Parts[i] = part;
        }

        // attack 2 : right
        width = right_attack_2.getWidth();
        height = right_attack_2.getHeight();
        partWidth = 48;
        remainingWidth = width % partWidth;
        right_attack_2Parts = new BufferedImage[attack_2_Images_num];
        for (int i = 0; i < attack_2_Images_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == attack_2_Images_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = right_attack_2.getSubimage(x, y, partWidth, height);
            right_attack_2Parts[i] = part;
        }

        // attack 2 : left
        width = left_attack_2.getWidth();
        height = left_attack_2.getHeight();
        partWidth = 48;
        remainingWidth = width % partWidth;
        left_attack_2Parts = new BufferedImage[attack_2_Images_num];
        for (int i = 0; i < attack_2_Images_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == attack_2_Images_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = left_attack_2.getSubimage(x, y, partWidth, height);
            left_attack_2Parts[i] = part;
        }

        // attack 3 : right
        width = right_attack_3.getWidth();
        height = right_attack_3.getHeight();
        partWidth = 48;
        remainingWidth = width % partWidth;
        right_attack_3Parts = new BufferedImage[attack_3_Images_num];
        for (int i = 0; i < attack_3_Images_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == attack_3_Images_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = right_attack_3.getSubimage(x, y, partWidth, height);
            right_attack_3Parts[i] = part;
        }

        // attack 3 : left
        width = left_attack_3.getWidth();
        height = left_attack_3.getHeight();
        partWidth = 48;
        remainingWidth = width % partWidth;
        left_attack_3Parts = new BufferedImage[attack_3_Images_num];
        for (int i = 0; i < attack_3_Images_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == attack_3_Images_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = left_attack_3.getSubimage(x, y, partWidth, height);
            left_attack_3Parts[i] = part;
        }
    }

    public String getSuitName() {
        return suitName;
    }

    private static BufferedImage mirrorHorizontally(BufferedImage image) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }

    /*
    Pro getters which handle the sprite between images as the frame goes on
     */

    // variables
    private final int framePerUpdate = 4;
    private final int framePerAttackUpdate = 6;

    private int roundFrame = 0;

    private int idleIndex = 0;
    private int idleLeftIndex = idleImages_num;
    private int jumpIndex = 0;
    private int jumpBackIndex = jumpImages_num;
    private int runIndex = 0;
    private int runBackIndex = runImages_num;
    private int attackIndex = 0;

    public BufferedImage getIdle_RightFrame() {
        roundFrame ++;
        if(roundFrame == framePerUpdate) {
            roundFrame = 0;
            idleIndex = (idleIndex + 1) % idleImages_num;
        }
        return idle_rightParts[idleIndex] ;
    }

    public BufferedImage getIdle_LeftFrame() {
        roundFrame ++;
        if(roundFrame == framePerUpdate) {
            roundFrame = 0;
            idleLeftIndex = (idleLeftIndex - 1 + idleImages_num) % idleImages_num;
            if (idleLeftIndex < 0) {
                idleLeftIndex = idleImages_num - 1;
            }
        }
        return idle_leftParts[idleLeftIndex] ;
    }

    public BufferedImage getJumpFrame() {
        roundFrame ++;
        if(roundFrame == framePerUpdate) {
            roundFrame = 0;
            jumpIndex = (jumpIndex + 1) % jumpImages_num;
        }
        return jump_rightParts[jumpIndex] ;
    }

    public BufferedImage getJumpBackFrame() {
        roundFrame ++;
        if(roundFrame == framePerUpdate) {
            roundFrame = 0;
            jumpBackIndex = (jumpBackIndex - 1 + jumpImages_num) % jumpImages_num;
            if (jumpBackIndex < 0) {
                jumpBackIndex = runImages_num - 2;
            }
        }
        return jump_leftParts[jumpBackIndex] ;
    }

    public BufferedImage getRunFrame() {
        roundFrame ++;
        if(roundFrame == framePerUpdate) {
            roundFrame = 0;
            runIndex = (runIndex + 1) % runImages_num;
        }
        return runParts[runIndex] ;
    }

    public BufferedImage getRunBackFrame() {
        roundFrame ++;
        if(roundFrame == framePerUpdate) {
            roundFrame = 0;
            runBackIndex = (runBackIndex - 1 + runImages_num) % runImages_num;
            if (runBackIndex < 0) {
                runBackIndex = runImages_num - 2;
            }
        }
        return runBackParts[runBackIndex] ;
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
            attackIndex = (attackIndex + 1) % attack_1_Images_num;
        }
        return right_attack_1Parts[attackIndex] ;
    }

    private BufferedImage getLeftAttack_1Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % attack_1_Images_num;
        }
        return left_attack_1Parts[attackIndex] ;
    }

    private BufferedImage getRightAttack_2Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % attack_2_Images_num;
        }
        return right_attack_2Parts[attackIndex] ;
    }

    private BufferedImage getLeftAttack_2Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % attack_2_Images_num;
        }
        return left_attack_2Parts[attackIndex] ;
    }

    private BufferedImage getRightAttack_3Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % attack_3_Images_num;
        }
        return right_attack_3Parts[attackIndex] ;
    }

    private BufferedImage getLeftAttack_3Frame() {
        roundFrame ++;
        if(roundFrame == framePerAttackUpdate) {
            roundFrame = 0;
            attackIndex = (attackIndex + 1) % attack_3_Images_num;
        }
        return left_attack_3Parts[attackIndex] ;
    }

}
