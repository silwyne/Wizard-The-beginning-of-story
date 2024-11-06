package nilian.online.connector.joiner;

import nilian.Player.Player;
import nilian.Player.PlayerSchema;
import nilian.online.connector.message.MessageProcessor;
import nilian.online.message.PlayerMessageType;
import nilian.online.message.ServerMessage;
import nilian.online.message.ServerMessageType;
import nilian.online.render.OnlineRenderer;

public class ClientMessageProcessor implements MessageProcessor<ServerMessage> {

    private final OnlineRenderer onlineRenderer;

    public ClientMessageProcessor(OnlineRenderer onlineRenderer) {
        this.onlineRenderer = onlineRenderer;
    }

    @Override
    public void process(ServerMessage message) {

        // if player comes to game as new
        if(message.getType().equals(ServerMessageType.SERVER_MESSAGE_TYPE_WELCOME)
                || message.getPlayer().getType().equals(PlayerMessageType.PLAYER_MESSAGE_TYPE_SPAWN)) {
            PlayerSchema playerSchema = new PlayerSchema(
                    message.getPlayer().getName(),
                    (int) message.getPlayer().getPlayerHash(),
                    message.getPlayer().getX(),
                    message.getPlayer().getY(),
                    Player.getRandomColor(),
                    48
            );
            // so add him as players  in game
            onlineRenderer.addPlayer(playerSchema);
        }

        // if player moves
        else if(message.getType().equals(ServerMessageType.SERVER_MESSAGE_TYPE_PLAYER)
                && message.getPlayer().getType().equals(PlayerMessageType.PLAYER_MESSAGE_TYPE_MOVE)) {
            PlayerSchema playerSchema = new PlayerSchema(
                    message.getPlayer().getName(),
                    (int) message.getPlayer().getPlayerHash(),
                    message.getPlayer().getX(),
                    message.getPlayer().getY(),
                    Player.getRandomColor(),
                    48
            );
            // so update his location
            onlineRenderer.updatePlayer(playerSchema);
        }
    }
}
