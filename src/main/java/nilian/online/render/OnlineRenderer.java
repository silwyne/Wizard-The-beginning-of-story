package nilian.online.render;

import nilian.Player.suit.PlayerImages;
import nilian.Player.PlayerSchema;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Handles events and players coming and going
 * We pass this class to the GamePanel so the GamePanel uses it to update everything!
 * This acts like a connector
 */
public class OnlineRenderer {

    private final PlayerImages playerImages;
    private List<PlayerSchema> otherPlayersInGame = new ArrayList<>() ;

    public OnlineRenderer() {
        // loading suit 1 images
        try {
            playerImages = new PlayerImages();
            playerImages.getImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        playerImages.separate();
    }

    public void addPlayer(PlayerSchema playerSchema) {
        otherPlayersInGame.add(playerSchema);
    }

    public void removePlayer(PlayerSchema playerSchema) {
        otherPlayersInGame.removeIf(schema -> schema.getClientHashCode() == playerSchema.getClientHashCode());
    }

    public void updatePlayer(PlayerSchema playerSchema) {
        System.out.println("PLAYER UPDATE: x:"+playerSchema.getPlayerX()+" y:"+playerSchema.getPlayerY());
        for (PlayerSchema schema : otherPlayersInGame) {
            if (schema.getClientHashCode() == playerSchema.getClientHashCode()) {
                schema.setPlayerX(playerSchema.getPlayerX());
                schema.setPlayerY(playerSchema.getPlayerY());
                return;
            }
        }
        otherPlayersInGame.add(playerSchema);
    }

    // draws other players on screen
    public void draw(Graphics2D g2) {
        BufferedImage image = playerImages.getIdleParts()[0].image ;
        for(PlayerSchema schema: otherPlayersInGame) {
            g2.drawImage(image, schema.getPlayerX(), schema.getPlayerY(), schema.getPlayerSize(), schema.getPlayerSize() , null) ;

            // Set up the font and color for the text
            g2.setFont(new Font("Arial", Font.BOLD, 12)); // Adjust font and size as needed
            g2.setColor(schema.getPlayerColor());

            // The text you want to display
            String text = schema.getPlayerName(); // Replace with actual player name or desired text

            // Calculate the position for the text
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textX = schema.getPlayerX() + (schema.getPlayerSize() / 2) - (textWidth / 2); // Center the text above the player
            int textY = schema.getPlayerY() - 2; // 2 pixels above the player, adjust as needed

            // Draw the text
            g2.drawString(text, textX, textY);
        }
    }
}
