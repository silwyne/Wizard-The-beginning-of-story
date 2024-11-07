package nilian.online.connector.joiner;

import nilian.Player.PlayerDirection;
import nilian.Player.PlayerSchema;
import nilian.online.connector.message.MessageProcessor;
import nilian.online.message.ServerMessage;
import nilian.online.render.OnlineRenderer;

import java.awt.*;

public class ClientMessageProcessor implements MessageProcessor<ServerMessage> {

    private final OnlineRenderer onlineRenderer;

    public ClientMessageProcessor(OnlineRenderer onlineRenderer) {
        this.onlineRenderer = onlineRenderer;
    }

    @Override
    public void process(ServerMessage message) {
        System.out.println("Message from : "+message.getPlayer().getName());
        PlayerDirection direction ;
        try  {
            direction = PlayerDirection.valueOf(message.getPlayer().getDirection());
        } catch (IllegalArgumentException e) {
            direction = PlayerDirection.idle;
            System.out.println(e.getMessage());
        }
        PlayerSchema playerSchema = new PlayerSchema(
                    message.getPlayer().getName(),
                    (int) message.getPlayer().getPlayerHash(),
                    message.getPlayer().getX(),
                    message.getPlayer().getY(),
                    Color.getColor(message.getPlayer().getNameColor()),
                    48,
                direction
            );
        // so update his location
        onlineRenderer.updatePlayer(playerSchema);
    }
}
