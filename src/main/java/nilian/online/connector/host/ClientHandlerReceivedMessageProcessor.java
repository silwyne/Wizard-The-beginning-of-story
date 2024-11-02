package nilian.online.connector.host;

import com.google.protobuf.Message;
import nilian.online.connector.message.MessageProcessor;
import nilian.online.connector.message.MessageWriter;

import java.util.ArrayList;

public class ClientHandlerReceivedMessageProcessor implements MessageProcessor {

    private final ArrayList<ClientHandler> allOtherClients;
    private final MessageWriter messageWriter;
    private final int clientHashCode;

    public ClientHandlerReceivedMessageProcessor(ArrayList<ClientHandler> allClients, MessageWriter messageWriter, int clientHashCode) {
        this.messageWriter = messageWriter;
        this.allOtherClients = allClients;
        this.clientHashCode = clientHashCode;
    }

    /**
     * This act is named BroadCast!
     * @param message message received
     */
    @Override
    public void process(Message message) {
        for(ClientHandler client : allOtherClients)
        {
            if(client != null && message != null) {
                if(client.clientHashcode != this.clientHashCode) {
                    messageWriter.send(message);
                }
            }
        }
    }

}
