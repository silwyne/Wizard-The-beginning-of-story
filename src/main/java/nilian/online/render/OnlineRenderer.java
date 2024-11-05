package nilian.online.render;

import nilian.Player.PlayerSchema;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Handles events and players coming and going
 * We pass this class to the GamePanel so the GamePanel uses it to update everything!
 * This acts like a connector
 */
public class OnlineRenderer {

    private List<PlayerSchema> otherPlayersInGame = new ArrayList<>() ;

    public OnlineRenderer() {}

    // draws other players on screen
    public void draw(Graphics2D g2) {}
}
