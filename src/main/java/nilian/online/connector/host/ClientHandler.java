package nilian.online.connector.host;


import nilian.online.connector.message.MessageListener;
import nilian.online.connector.message.MessageWriter;
import nilian.online.message.ClientMessage;
import nilian.online.message.PlayerMessage;
import nilian.online.message.ServerMessage;

import java.net.Socket;
import java.util.ArrayList;

/**
 * This class serves the GameServer and only handles one client!
 * Listens to its messages and writes message to it or others!
 */
public class ClientHandler {

    //List of Shared Connections between all Clients!
    public int clientHashcode ;
    private final MessageListener<ClientMessage> messageListener;
    private final MessageWriter<ServerMessage> messageWriter;
    private PlayerMessage lastPlayerMessage;

    public ClientHandler(Socket socket, ArrayList<ClientHandler> allOtherClients) {
        this.messageWriter = new MessageWriter<>(socket);
        this.clientHashcode = (System.currentTimeMillis() * 33 + "client").hashCode();
        messageListener =
                new MessageListener<ClientMessage>(socket,
                new ServerMessageProcessor(allOtherClients, this),
                ClientMessage.parser());
    }

    /**
     * This functions starts clientHandler listening to his client!
     * We consider this part server listening to client!
     */
    public void startMessageListener() {
        messageListener.start();
    }

    public MessageWriter<ServerMessage> getMessageWriter() {
        return messageWriter;
    }

    public PlayerMessage getLastPlayerMessage() {
        return lastPlayerMessage;
    }

    public void setLastPlayerMessage(PlayerMessage lastPlayerMessage) {
        this.lastPlayerMessage = lastPlayerMessage;
    }
}
