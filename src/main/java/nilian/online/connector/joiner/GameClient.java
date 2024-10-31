package nilian.online.connector.joiner;

import com.google.protobuf.CodedOutputStream;
import nilian.online.connector.message.MessageListener;
import nilian.online.connector.message.MessageWriter;
import nilian.online.message.ClientMessage;

import java.io.*;
import java.net.Socket;

public class GameClient {

    private Socket socket;
    private final String serverIP;
    private final int serverPort;

    private BufferedReader bufferedReader ;
    private BufferedWriter bufferedWriter;
    private final String username ;
    private final int userHash ;

    private MessageListener messageListener;
    private MessageWriter messageWriter;

    public GameClient(String username, String serverIP, int serverPort) {
        this.username = username;
        this.serverIP = serverIP;
        this.serverPort = serverPort;

        // generate unique hash for user
        userHash = ((System.currentTimeMillis() * 100 + username).hashCode());
    }

    /**
     * Connects you to GameServer
     * @throws IOException I don't know
     */
    public void connect() throws IOException {
        if(socket == null) {
            socket = new Socket(serverIP, serverPort);

            messageListener = new MessageListener(this.socket, new ClientMessageProcessor());
            messageWriter = new MessageWriter(this.socket);
            System.out.println("Connected to server at " + serverIP + ":" + serverPort);
        } else {
            System.out.println("Already connected: Socket Object is not null");
        }
    }

    public void connect(Socket connectedSocket) {
        if(socket == null) {
            socket = connectedSocket;
            messageListener = new MessageListener(this.socket, new ClientMessageProcessor());
            messageWriter = new MessageWriter(this.socket);
            System.out.println("Connected to server at " + serverIP + ":" + serverPort);
        } else {
            System.out.println("Already connected: Socket Object is not null");
        }
    }

    /**
    * Simply sends message to server.
    * @param message message string!
    */
    public void sendMessage(ClientMessage message) {
        messageWriter.send(message);
    }

    /**
     * Listens for other Clients messages!
     * Actually from server which broadcasts other client messages  to me!
     */
    public void listenForMessage() {
        messageListener.start();
    }

    public void introduceToServer() {
        // TODO: Implement introduction logics
    }
}