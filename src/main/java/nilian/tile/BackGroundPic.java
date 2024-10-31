package nilian.tile;

import nilian.game.panel.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackGroundPic {

    public final BufferedImage backgroundImage;
    private final GamePanel gamePanel;

    public BackGroundPic(GamePanel gamePanel, String imagePath) {
        this.gamePanel = gamePanel;

        try {
            // Load the original image
            BufferedImage originalImage = gamePanel.getTileM().getTileImage(imagePath).image;

            // Get the GraphicsConfiguration of the current screen
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();

            // Create a compatible image with the size of the game panel
            backgroundImage = gc.createCompatibleImage(
                    gamePanel.screenWidth,
                    gamePanel.screenHeight,
                    Transparency.OPAQUE
            );

            // Draw the original image onto the compatible image, scaling it to fit
            Graphics2D g2d = backgroundImage.createGraphics();
            g2d.drawImage(originalImage, 0, 0, gamePanel.screenWidth, gamePanel.screenHeight, null);
            g2d.dispose();

        } catch (Exception e) {
            throw new RuntimeException("Failed to load background image: " + imagePath, e);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(backgroundImage, 0, 0, null);
    }
}
