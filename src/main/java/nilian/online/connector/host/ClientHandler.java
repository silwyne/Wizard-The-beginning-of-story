package nilian.online.connector.host;


import nilian.online.connector.message.MessageListener;
import nilian.online.connector.message.MessageWriter;

import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {

    //List of Shared Connections between all Clients!
    public static ArrayList<ClientHandler> allOtherClients = new ArrayList<>();
    public int clientHashcode ;
    private final MessageListener messageListener;

    public ClientHandler(Socket socket) {

        this.clientHashcode = (System.currentTimeMillis() * 33 + "client").hashCode();
        allOtherClients.add(this) ;
        MessageWriter messageWriter = new MessageWriter(socket);
        messageListener =
                new MessageListener(socket, new ClientHandlerReceivedMessageProcessor(allOtherClients, messageWriter, clientHashcode));
    }

    public void startMessageListener() {
        messageListener.start();
    }

}
