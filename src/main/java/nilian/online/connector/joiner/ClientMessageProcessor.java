package nilian.online.connector.joiner;

import com.google.protobuf.Message;
import nilian.online.connector.message.MessageProcessor;

public class ClientMessageProcessor implements MessageProcessor {
    @Override
    public void process(Message message) {
        System.out.println(message);
    }
}
