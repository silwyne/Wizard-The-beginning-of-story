package nilian.online.connector.joiner;

import nilian.Player.PlayerOrientation;
import nilian.Player.PlayerState;
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
        System.out.println("Message : "+message);
        PlayerState direction ;
        try  {
            direction = PlayerState.valueOf(message.getPlayer().getState());
        } catch (IllegalArgumentException e) {
            direction = PlayerState.IDLE;
            System.out.println(e.getMessage());
        }
        PlayerSchema playerSchema = new PlayerSchema(
                message.getPlayer().getName(),
                (int) message.getPlayer().getPlayerHash(),
                message.getPlayer().getX(),
                message.getPlayer().getY(),
                stringToColor(message.getPlayer().getNameColor()),
                48,
                direction,
                message.getPlayer().getSuitCode(),
                PlayerOrientation.valueOf(message.getPlayer().getOrientation())
            );
        // so update his location
        onlineRenderer.updatePlayer(playerSchema);
    }

    public static Color stringToColor(String colorString) {
        String[] rgb = colorString.split(",");
        int r = Integer.parseInt(rgb[0]);
        int g = Integer.parseInt(rgb[1]);
        int b = Integer.parseInt(rgb[2]);
        return new Color(r, g, b);
    }
}
