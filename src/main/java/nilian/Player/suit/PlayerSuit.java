package nilian.player.suit;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

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

    public BufferedImage[] idleRightParts;
    public BufferedImage[] idleLeftParts;
    public BufferedImage[] jumpRightParts;
    public BufferedImage[] jumpLeftParts;
    public BufferedImage[] runParts;
    public BufferedImage[] runBackParts;
    public BufferedImage[] rightAttack_1_Parts;
    public BufferedImage[] leftAttack_1_Parts;
    public BufferedImage[] rightAttack_2_Parts;
    public BufferedImage[] leftAttack_2_Parts;
    public BufferedImage[] rightAttack_3_Parts;
    public BufferedImage[] leftAttack_3_Parts;

    public int idleImages_num;
    public int jumpImages_num;
    public int runImages_num;
    public int attack_1_images_num;
    public int attack_2_images_num;
    public int attack_3_images_num;

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
        this.attack_1_images_num = attack_1;
        this.attack_2_images_num = attack_2;
        this.attack_3_images_num = attack_3;
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

    public void loadFrames() {

        idleRightParts = getImageParts(idle_right, idleImages_num);
        idleLeftParts = getImageParts(idle_left, idleImages_num);

        jumpRightParts = getImageParts(jump_right, jumpImages_num);;
        jumpLeftParts = getImageParts(jump_left, jumpImages_num);

        runParts = getImageParts(run, runImages_num);
        runBackParts = getImageParts(runBack, runImages_num);

        rightAttack_1_Parts = getImageParts(right_attack_1, attack_1_images_num);
        leftAttack_1_Parts = getImageParts(left_attack_1, attack_1_images_num);

        rightAttack_2_Parts = getImageParts(right_attack_2, attack_2_images_num);
        leftAttack_2_Parts = getImageParts(left_attack_2, attack_2_images_num);

        rightAttack_3_Parts = getImageParts(right_attack_3, attack_3_images_num);
        leftAttack_3_Parts = getImageParts(left_attack_3, attack_3_images_num);
    }

    private static BufferedImage[] getImageParts(BufferedImage image, int partsNumber) {
        int width = image.getWidth();
        int height = image.getHeight();
        int partWidth = 48;
        int remainingWidth = width % partWidth;
        BufferedImage[] imageParts = new BufferedImage[partsNumber];
        for (int i = 0; i < partsNumber; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == partsNumber - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = image.getSubimage(x, y, partWidth, height);
            imageParts[i] = part;
        }
        return imageParts;
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

}
