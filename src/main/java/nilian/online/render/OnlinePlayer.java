package nilian.online.render;

import nilian.Player.PlayerSchema;
import nilian.Player.suit.PlayerFrameProvider;

public class OnlinePlayer {
    PlayerSchema schema;
    PlayerFrameProvider frameProvider;

    public OnlinePlayer(PlayerSchema schema, PlayerFrameProvider frameProvider) {
        this.schema = schema;
        this.frameProvider = frameProvider;
    }
}
