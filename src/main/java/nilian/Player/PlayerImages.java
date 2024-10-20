package nilian.Player;

import nilian.entity.Entity;
import nilian.entity.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Handles player images
 */
public class PlayerImages {

    Entity entity = new Entity();
    Image[] idleParts;
    Image[] jumpParts;
    Image[] runParts;
    Image[] runBackParts;


    /**
     * Reads basic frames
     * @throws IOException maybe the file doesn't exist!
     */
    public void getImages() throws IOException {
        entity.idle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Cyborg_idle.png"))) ;
        entity.jump = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Cyborg_jump.png"))) ;
        entity.run = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Cyborg_run.png"))) ;
        entity.runback = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Cyborg_run-backward.png"))) ;
    }

    /**
     * Simply cuts the images of player to make them usable for me
     */
    public void separate()
    {
        int width = entity.idle.getWidth();
        int height = entity.idle.getHeight();
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
            BufferedImage part = entity.idle.getSubimage(x, y, partWidth, partHeight);

            idleParts[i] = new Image();
            idleParts[i].image = part;
        }

        width = entity.jump.getWidth();
        height = entity.jump.getHeight();
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
            BufferedImage part = entity.jump.getSubimage(x, y, partWidth, partHeight);

            jumpParts[i] = new Image();
            jumpParts[i].image = part;
        }

        width = entity.run.getWidth();
        height = entity.run.getHeight();
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
            BufferedImage part = entity.run.getSubimage(x, y, partWidth, partHeight);

            runParts[i] = new Image();
            runParts[i].image = part;
        }

        width = entity.runback.getWidth();
        height = entity.runback.getHeight();
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
            BufferedImage part = entity.runback.getSubimage(x, y, partWidth, partHeight);

            runBackParts[i] = new Image();
            runBackParts[i].image = part;
        }
    }

}
