package nilian.Player.suit;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PlayerSuit {

    private final String pathToImagePack;

    private BufferedImage idle;
    private BufferedImage jump;
    private BufferedImage run;
    private BufferedImage runBack;

    private BufferedImage[] idleParts;
    private BufferedImage[] jumpParts;
    private BufferedImage[] runParts;
    private BufferedImage[] runBackParts;

    private int idleImages_num;
    private int jumpImages_num;
    private int runImages_num;
    private int runBackImages_num;


    public PlayerSuit(String pathToImagePack) {
        this.pathToImagePack = pathToImagePack;
    }


    /**
     * Sets the number of images of each act
     */
    public void setImagesNum(int idleN, int jumpN, int runN, int runBackN) {
        this.idleImages_num = idleN;
        this.jumpImages_num = jumpN;
        this.runImages_num = runN;
        this.runBackImages_num = runBackN;
    }

    /**
     * reads raw images
     */
    public void getImages() {
        try {
            idle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathToImagePack+"/idle.png"))) ;
            jump = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathToImagePack+"/jump.png"))) ;
            run = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathToImagePack+"/run.png"))) ;
            runBack = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathToImagePack+"/run-backward.png"))) ;
        } catch(IOException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Processes each image into images you want!
     * The final function which gets called is this to make all things done!
     */
    public void separate() {
        int width = idle.getWidth();
        int height = idle.getHeight();
        int partWidth = 48;
        int remainingWidth = width % partWidth;
        idleParts = new BufferedImage[idleImages_num];
        for (int i = 0; i < idleImages_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == idleImages_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = idle.getSubimage(x, y, partWidth, height);
            idleParts[i] = part;
        }

        width = jump.getWidth();
        height = jump.getHeight();
        partWidth = 48;
        remainingWidth = width % partWidth;
        jumpParts = new BufferedImage[jumpImages_num];
        for (int i = 0; i < jumpImages_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == jumpImages_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = jump.getSubimage(x, y, partWidth, height);
            jumpParts[i] = part;
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
        runBackParts = new BufferedImage[runBackImages_num];
        for (int i = 0; i < runBackImages_num; i++) {
            int x = i * partWidth;
            int y = 0;
            if (i == runBackImages_num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = runBack.getSubimage(x, y, partWidth, height);
            runBackParts[i] = part;
        }
    }

    /*
    Pro getters which handle the sprite between images as the frame goes on
     */

    // variables
    private final int updatePerFrame = 3;
    private int roundFrame = 0;

    private int idleIndex = 0;
    private int jumpIndex = 0;
    private int runIndex = 0;
    private int runBackIndex = 0;

    public BufferedImage getIdleFrame() {
        roundFrame ++;
        if(roundFrame == updatePerFrame) {
            roundFrame = 0;
            idleIndex = (idleIndex + 1) % idleImages_num;
        }
        return idleParts[idleIndex] ;
    }

    public BufferedImage getJumpFrame() {
        roundFrame ++;
        if(roundFrame == updatePerFrame) {
            roundFrame = 0;
            jumpIndex = (jumpIndex + 1) % jumpImages_num;
        }
        return jumpParts[jumpIndex] ;
    }

    public BufferedImage getRunFrame() {
        roundFrame ++;
        if(roundFrame == updatePerFrame) {
            roundFrame = 0;
            runIndex = (runIndex + 1) % runImages_num;
        }
        return runParts[runIndex] ;
    }

    public BufferedImage getRunBackFrame() {
        roundFrame ++;
        if(roundFrame == updatePerFrame) {
            roundFrame = 0;
            runBackIndex = (runBackIndex + 1) % runBackImages_num;
        }
        return runBackParts[runBackIndex] ;
    }
}
