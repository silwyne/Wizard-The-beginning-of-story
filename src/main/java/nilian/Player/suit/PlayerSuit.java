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

    public BufferedImage[] idle_rightParts;
    public BufferedImage[] idle_leftParts;
    public BufferedImage[] jump_rightParts;
    public BufferedImage[] jump_leftParts;
    public BufferedImage[] runParts;
    public BufferedImage[] runBackParts;
    public BufferedImage[] right_attack_1Parts;
    public BufferedImage[] left_attack_1Parts;
    public BufferedImage[] right_attack_2Parts;
    public BufferedImage[] left_attack_2Parts;
    public BufferedImage[] right_attack_3Parts;
    public BufferedImage[] left_attack_3Parts;

    public int idleImages_num;
    public int jumpImages_num;
    public int runImages_num;
    public int attack_1_Images_num;
    public int attack_2_Images_num;
    public int attack_3_Images_num;

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



}
