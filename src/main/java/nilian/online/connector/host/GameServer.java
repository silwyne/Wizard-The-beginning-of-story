package nilian.online.connector.host;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This class accepts new connection
 * Then set new Threads for them to keep Connection alive
 */
public class GameServer {

    private static final ArrayList<ClientHandler> allClients = new ArrayList<>();
    private final int serverPort;
    private ServerSocket serverSocket;

    public GameServer(int serverPort) {
        this.serverPort = serverPort;
        setServer();
        System.out.println("SERVER: server is up!");
    }

    /**
     * Gets the ports for its own and makes the server!
     */
    public void setServer(){
        try {
            this.serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
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
            // handling new connection
            ClientHandler clientHandler = new ClientHandler(clientSocket, allClients);
            clientHandler.startMessageListener();
            // adding to connected clients
            allClients.add(clientHandler);
            connected_clients ++ ;
        }
    }

}