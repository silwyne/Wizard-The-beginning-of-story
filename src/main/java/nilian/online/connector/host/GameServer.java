package nilian.online.connector.host;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import nilian.online.message.ServerMessage;
import nilian.online.message.ServerMessageType;

/**
 * This class accepts new connection
 * Then set new Threads for them to keep Connection alive
 */
public class GameServer {

    private static final ArrayList<ClientHandler> allClients = new ArrayList<>();
    private ServerSocket serverSocket;

    public GameServer(int serverPort) {
        try {
            this.serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        System.out.println("[SERVER]: server is up on port: "+ serverPort);
    }

    /**
     * Waits for new connections
     */
    public void startServer() {
        int connected_clients = 0;
        int MAX_CONNECTED_CLIENTS = 10;
        while(true) {
            if(connected_clients == MAX_CONNECTED_CLIENTS) {
                System.out.println("No More Clients Can Connect !");
                break;
            }
            // wait for new connection
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("SERVER: Some Client Connected to Server");
            // handling new connection
            ClientHandler clientHandler = new ClientHandler(clientSocket, allClients);
            clientHandler.startMessageListener();
            // informing new client
            informNewClient(clientHandler);
            // adding to connected clients
            allClients.add(clientHandler);
            connected_clients ++ ;
        }
    }

    /**
     * sends latest updates of players in game to new client
     * @param newClient the client who connected to server recently
     */
    private void informNewClient(ClientHandler newClient) {
        
        for(ClientHandler clientHandler: allClients) {
            
            ServerMessage clientMessage = ServerMessage.newBuilder()
                .setType(ServerMessageType.SERVER_MESSAGE_TYPE_WELCOME)
                .setPlayer(clientHandler.getLastPlayerMessage())
                .build();

            newClient.getMessageWriter().send(clientMessage);
        }
    }

}