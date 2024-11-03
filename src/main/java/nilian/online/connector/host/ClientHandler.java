package nilian.online.connector.host;


import nilian.online.connector.message.MessageListener;
import nilian.online.connector.message.MessageWriter;
import nilian.online.message.ClientMessage;
import nilian.online.message.ServerMessage;

import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {

    //List of Shared Connections between all Clients!
    public static ArrayList<ClientHandler> allOtherClients = new ArrayList<>();
    public int clientHashcode ;
    private final MessageListener<ClientMessage> messageListener;

    public ClientHandler(Socket socket) {

        this.clientHashcode = (System.currentTimeMillis() * 33 + "client").hashCode();
        allOtherClients.add(this) ;
        MessageWriter<ServerMessage> messageWriter = new MessageWriter<>(socket);
        messageListener =
                new MessageListener<ClientMessage>(socket,
                new ClientHandlerReceivedMessageProcessor(allOtherClients, messageWriter, clientHashcode),
                ClientMessage.parser());
    }

    /**
     * This functions starts clientHandler listening to his client!
     * We consider this part server listening to client!
     */
    public void startMessageListener() {
        messageListener.start();
    }

}
