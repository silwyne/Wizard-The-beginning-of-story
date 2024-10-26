package nilian.online.host;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    private final int serverPort;

    private ServerSocket serverSocket;


    public GameServer(int serverPort) throws IOException {
        this.serverPort = serverPort;
        this.serverSocket = new ServerSocket(serverPort);
    }


    public void startServer() throws IOException {
        while(true) {
            // wait for new connection
            Socket clientSocket = serverSocket.accept();
            // handling new connection
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }

}