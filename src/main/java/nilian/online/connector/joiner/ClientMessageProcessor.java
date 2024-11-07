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
        System.out.println("Message from : "+message.getPlayer().getName());
//        PlayerSchema playerSchema = new PlayerSchema(
//                    message.getPlayer().getName(),
//                    (int) message.getPlayer().getPlayerHash(),
//                    message.getPlayer().getX(),
//                    message.getPlayer().getY(),
//                    Player.getRandomColor(),
//                    48
//            );
//        // so update his location
//        onlineRenderer.updatePlayer(playerSchema);
    }
}
