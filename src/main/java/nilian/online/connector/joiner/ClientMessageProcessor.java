package nilian.online.connector.joiner;

import nilian.online.connector.message.MessageProcessor;
import nilian.online.message.ServerMessage;

public class ClientMessageProcessor implements MessageProcessor<ServerMessage> {

    @Override
    public void process(ServerMessage message) {
        System.out.println(message);
    }
}
