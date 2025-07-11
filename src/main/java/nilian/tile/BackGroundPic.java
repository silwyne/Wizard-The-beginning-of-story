package nilian.tile;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

/**
 * Provides background picture for game panel
 */
public class BackGroundPic {

    public final BufferedImage backgroundImage;

    /**
     * Makes an instance of BackGroundPic Class
     * @param gamePanel the game panel you need the background for
     * @param imagePath path to background image
     */
    public BackGroundPic(String imagePath, int width, int height) {

        try {
            // Load the original image
            BufferedImage originalImage = new TileManager().getTileImage(imagePath).image;

            // Get the GraphicsConfiguration of the current screen
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();

            // Create a compatible image with the size of the game panel
            backgroundImage = gc.createCompatibleImage(
                width,
                height,
                Transparency.OPAQUE
            );

            // Draw the original image onto the compatible image, scaling it to fit
            Graphics2D g2d = backgroundImage.createGraphics();
            g2d.drawImage(originalImage, 0, 0, width, height, null);
            g2d.dispose();

        } catch (HeadlessException e) {
            throw new RuntimeException("Failed to load background image: " + imagePath, e);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(backgroundImage, 0, 0, null);
    }
}
