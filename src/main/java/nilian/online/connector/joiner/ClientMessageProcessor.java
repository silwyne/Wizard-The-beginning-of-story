package nilian.online.connector.joiner;

import nilian.online.connector.message.MessageProcessor;
import nilian.online.message.ServerMessage;
import nilian.online.render.OnlineRenderer;

public class ClientMessageProcessor implements MessageProcessor<ServerMessage> {

    private final OnlineRenderer onlineRenderer;

    public ClientMessageProcessor(OnlineRenderer onlineRenderer) {
        this.onlineRenderer = onlineRenderer;
    }

    @Override
    public void process(ServerMessage message) {

    }
}
