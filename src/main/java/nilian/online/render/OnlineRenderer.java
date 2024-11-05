package nilian.online.render;

import nilian.Player.PlayerImages;
import nilian.Player.PlayerSchema;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Handles events and players coming and going
 * We pass this class to the GamePanel so the GamePanel uses it to update everything!
 * This acts like a connector
 */
public class OnlineRenderer {

    PlayerImages playerImages;
    private final PlayerSchema mySchema;
    private List<PlayerSchema> otherPlayersInGame = new ArrayList<>() ;

    public OnlineRenderer(PlayerSchema mySchema) {
        this.mySchema = mySchema;
        // loading suit 1 images
        try {
            playerImages = new PlayerImages();
            playerImages.getImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        playerImages.separate();
    }

    // draws other players on screen
    public void draw(Graphics2D g2) {
        for(PlayerSchema schema: otherPlayersInGame) {
//            g2.drawImage(playerImages, playerSchema.getPlayerX(), playerSchema.getPlayerY(), gamePanel.tileSize, gamePanel.tileSize , null) ;
//
//            // Set up the font and color for the text
//            g2.setFont(new Font("Arial", Font.BOLD, 12)); // Adjust font and size as needed
//            g2.setColor(playerNameColor);
//
//            // The text you want to display
//            String text = playerSchema.getPlayerName(); // Replace with actual player name or desired text
//
//            // Calculate the position for the text
//            FontMetrics fm = g2.getFontMetrics();
//            int textWidth = fm.stringWidth(text);
//            int textX = playerSchema.getPlayerX() + (gamePanel.tileSize / 2) - (textWidth / 2); // Center the text above the player
//            int textY = playerSchema.getPlayerY() - 2; // 2 pixels above the player, adjust as needed
//
//            // Draw the text
//            g2.drawString(text, textX, textY);
        }
    }
}
