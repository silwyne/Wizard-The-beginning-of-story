package nilian.online.connector.host;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    private final int serverPort;

    private ServerSocket serverSocket;


    public GameServer(int serverPort) {
        this.serverPort = serverPort;
        setServer();
    }

    public void setServer(){
        try {
            this.serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }


    public void startServer() {
        while(true) {
            // wait for new connection
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // handling new connection
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clientHandler.startMessageListener();
        }
    }

}