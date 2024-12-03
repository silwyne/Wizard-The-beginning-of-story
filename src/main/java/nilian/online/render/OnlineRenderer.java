package nilian.online.render;

import nilian.Player.Player;
import nilian.Player.PlayerSchema;
import nilian.Player.PlayerUpdater;
import nilian.Player.suit.SuitHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles events and players coming and going
 * We pass this class to the GamePanel so the GamePanel uses it to update everything!
 * This acts like a connector
 */
public class OnlineRenderer {

    private final List<PlayerSchema> otherPlayersInGame = new ArrayList<>() ;

    public OnlineRenderer() {}

    public void addPlayer(PlayerSchema playerSchema) {
        otherPlayersInGame.add(playerSchema);
    }

    public void removePlayer(PlayerSchema playerSchema) {
        otherPlayersInGame.removeIf(schema -> schema.getClientHashCode() == playerSchema.getClientHashCode());
    }

    public void updatePlayer(PlayerSchema playerSchema) {
        System.out.println("PLAYER UPDATE: x:"+playerSchema.getPlayerX()+" y:"+playerSchema.getPlayerY());
        boolean updated = false;
        for (PlayerSchema schema : otherPlayersInGame) {
            if (schema.getClientHashCode() == playerSchema.getClientHashCode()) {
                schema.setPlayerX(playerSchema.getPlayerX());
                schema.setPlayerY(playerSchema.getPlayerY());
                schema.setPlayerState(playerSchema.getPlayerState());
                updated = true;
                break;
            }
        }
        if(!updated){
            otherPlayersInGame.add(playerSchema);
        }
    }

    // draws other players on screen
    public void draw(Graphics2D g2) {
        BufferedImage image;
        for(PlayerSchema schema: otherPlayersInGame) {
            //State Image of Player
            image = PlayerUpdater.getPlayerImage(
                    schema.getPlayerState(),
                    SuitHandler.getSuit(schema.getSuitName()),
                    schema.getPlayerOrientation());

            // draw player
            g2.drawImage(image, schema.getPlayerX(), schema.getPlayerY(), schema.getPlayerSize(), schema.getPlayerSize() , null) ;

            // draw player name
            Player.drawPlayerName(g2, schema);
        }
    }
}
