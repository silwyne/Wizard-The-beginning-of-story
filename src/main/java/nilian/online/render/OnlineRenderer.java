package nilian.online.render;

import nilian.Player.Player;
import nilian.Player.PlayerSchema;
import nilian.Player.PlayerUpdater;
import nilian.Player.suit.PlayerFrameProvider;

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

    private final List<OnlinePlayer> otherPlayersInGame = new ArrayList<>() ;

    public OnlineRenderer() {}

    public void addPlayer(PlayerSchema playerSchema) {
        otherPlayersInGame.add(new OnlinePlayer(playerSchema, new PlayerFrameProvider(playerSchema.getSuitName())));
    }

    public void removePlayer(PlayerSchema playerSchema) {
        otherPlayersInGame.removeIf(player -> player.schema.getClientHashCode() == playerSchema.getClientHashCode());
    }

    public void updatePlayer(PlayerSchema playerSchema) {
        System.out.println("PLAYER UPDATE: x:"+playerSchema.getPlayerX()+" y:"+playerSchema.getPlayerY());
        boolean updated = false;
        for (OnlinePlayer player : otherPlayersInGame) {
            PlayerSchema schema = player.schema;
            if (schema.getClientHashCode() == playerSchema.getClientHashCode()) {
                schema.setPlayerX(playerSchema.getPlayerX());
                schema.setPlayerY(playerSchema.getPlayerY());
                schema.setPlayerState(playerSchema.getPlayerState());
                updated = true;
                break;
            }
        }
        if(!updated){
            addPlayer(playerSchema);
        }
    }

    // draws other players on screen
    public void draw(Graphics2D g2) {
        BufferedImage image;
        for(OnlinePlayer player: otherPlayersInGame) {
            PlayerSchema schema = player.schema;
            //State Image of Player
            image = PlayerUpdater.getPlayerImage(
                    schema.getPlayerState(),
                    player.frameProvider,
                    schema.getPlayerOrientation());

            // draw player
            g2.drawImage(image, schema.getPlayerX(), schema.getPlayerY(), schema.getPlayerSize(), schema.getPlayerSize() , null) ;

            // draw player name
            Player.drawPlayerName(g2, schema);
        }
    }
}
