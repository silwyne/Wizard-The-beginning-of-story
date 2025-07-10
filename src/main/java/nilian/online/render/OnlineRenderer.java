package nilian.online.render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import nilian.player.Player;
import nilian.player.PlayerSchema;
import nilian.player.PlayerUpdater;
import nilian.player.suit.PlayerFrameProvider;

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
                schema.setPlayerOrientation(playerSchema.getPlayerOrientation());
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
