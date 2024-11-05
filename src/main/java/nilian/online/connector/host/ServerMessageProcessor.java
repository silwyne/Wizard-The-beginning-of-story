package nilian.online.connector.host;

import com.google.protobuf.Message;
import nilian.online.MessageParser;
import nilian.online.connector.message.MessageProcessor;
import nilian.online.connector.message.MessageWriter;
import nilian.online.message.*;

import java.util.ArrayList;

public class ServerMessageProcessor implements MessageProcessor<ClientMessage> {

    private final ArrayList<ClientHandler> allOtherClients;
    private final MessageWriter<ServerMessage> messageWriter;
    private final int clientHashCode;

    public ServerMessageProcessor(ArrayList<ClientHandler> allClients, MessageWriter<ServerMessage> messageWriter, int clientHashCode) {
        this.messageWriter = messageWriter;
        this.allOtherClients = allClients;
        this.clientHashCode = clientHashCode;
    }

    /**
     * processes the message from one client and
     * decides to broadcast it or something else!
     * @param message message received
     */
    @Override
    public void process(ClientMessage message) {
        // first printing the message
        System.out.println(message);
        // sending message to other clients
        ServerMessage serverMessage = MessageParser.parse(message);
        broadCast(serverMessage);
    }

    /**
     * Broadcasts message to all other clients!
     * @param message message from one client
     */
    private void broadCast(ServerMessage message) {
        for(ClientHandler client : allOtherClients)
        {
            if(client != null) {
                if(client.clientHashcode != this.clientHashCode) {
                    messageWriter.send(message);
                }
            }
        }
    }
}
