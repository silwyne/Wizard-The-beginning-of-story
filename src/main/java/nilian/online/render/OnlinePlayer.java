package nilian.online.render;

import nilian.player.PlayerSchema;
import nilian.player.suit.PlayerFrameProvider;

public class OnlinePlayer {
    PlayerSchema schema;
    PlayerFrameProvider frameProvider;

    public OnlinePlayer(PlayerSchema schema, PlayerFrameProvider frameProvider) {
        this.schema = schema;
        this.frameProvider = frameProvider;
    }
}
