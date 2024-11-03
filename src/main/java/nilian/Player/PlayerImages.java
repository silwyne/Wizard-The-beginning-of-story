package nilian.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Handles player images
 */
public class PlayerImages {

    PlayerEntity playerEntity = new PlayerEntity();
    Image[] idleParts;
    Image[] jumpParts;
    Image[] runParts;
    Image[] runBackParts;


    /**
     * Reads basic frames
     * @throws IOException maybe the file doesn't exist!
     */
    public void getImages() throws IOException {
        playerEntity.idle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Cyborg_idle.png"))) ;
        playerEntity.jump = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Cyborg_jump.png"))) ;
        playerEntity.run = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Cyborg_run.png"))) ;
        playerEntity.runBack = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Cyborg_run-backward.png"))) ;
    }

    /**
     * Simply cuts the images of player to make them usable for me
     */
    public void separate()
    {
        int width = playerEntity.idle.getWidth();
        int height = playerEntity.idle.getHeight();
        int partWidth = 48;
        int num = width / partWidth;
        int remainingWidth = width % partWidth;
        idleParts = new Image[num];
        for (int i = 0; i < num; i++) {
            int x = i * partWidth;
            int y = 0;
            int partHeight = height;
            if (i == num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = playerEntity.idle.getSubimage(x, y, partWidth, partHeight);

            idleParts[i] = new Image();
            idleParts[i].image = part;
        }

        width = playerEntity.jump.getWidth();
        height = playerEntity.jump.getHeight();
        partWidth = 48;
        num = width / partWidth;
        remainingWidth = width % partWidth;
        jumpParts = new Image[num];
        for (int i = 0; i < num; i++) {
            int x = i * partWidth;
            int y = 0;
            int partHeight = height;
            if (i == num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = playerEntity.jump.getSubimage(x, y, partWidth, partHeight);

            jumpParts[i] = new Image();
            jumpParts[i].image = part;
        }

        width = playerEntity.run.getWidth();
        height = playerEntity.run.getHeight();
        partWidth = 48;
        num = width / partWidth;
        remainingWidth = width % partWidth;
        runParts = new Image[num];
        for (int i = 0; i < num; i++) {
            int x = i * partWidth;
            int y = 0;
            int partHeight = height;
            if (i == num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = playerEntity.run.getSubimage(x, y, partWidth, partHeight);

            runParts[i] = new Image();
            runParts[i].image = part;
        }

        width = playerEntity.runBack.getWidth();
        height = playerEntity.runBack.getHeight();
        partWidth = 48;
        num = width / partWidth;
        remainingWidth = width % partWidth;
        runBackParts = new Image[num];
        for (int i = 0; i < num; i++) {
            int x = i * partWidth;
            int y = 0;
            int partHeight = height;
            if (i == num - 1 && remainingWidth > 0) {
                partWidth = remainingWidth;
            }
            BufferedImage part = playerEntity.runBack.getSubimage(x, y, partWidth, partHeight);

            runBackParts[i] = new Image();
            runBackParts[i].image = part;
        }
    }

}
