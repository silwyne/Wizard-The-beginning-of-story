package nilian.online.connector.joiner;

import nilian.online.connector.message.MessageListener;
import nilian.online.connector.message.MessageWriter;
import nilian.online.message.*;
import nilian.online.render.OnlineRenderer;

import java.io.*;
import java.net.Socket;

/**
 * Connects to server and sends and receives messages from and to server!
 */
public class GameClient {

    private Socket socket;
    private final String serverIP;
    private final int serverPort;

    private OnlineRenderer onlineRenderer;

    private final String username ;
    private int userHash ;

    private MessageListener<ServerMessage> messageListener;
    private MessageWriter<ClientMessage> messageWriter;

    private PlayerMessage playerMessage;
    private ClientMessage introductionMessage;


    public GameClient(String username, String serverIP, int serverPort) {
        this.username = username;
        this.serverIP = serverIP;
        this.serverPort = serverPort;

        // generate unique hash for user
        userHash = ((System.currentTimeMillis() * 100 + username).hashCode());
        // initializing the onlineRenderer
        onlineRenderer = new OnlineRenderer();
    }

    /**
     * Connects you to GameServer
     * @throws IOException I don't know
     */
    public void connect() throws IOException {
        if(socket == null) {
            socket = new Socket(serverIP, serverPort);
            // initializing MessageListener Object which listens to server messages
            messageListener = new MessageListener<ServerMessage>(
                    this.socket,
                    new ClientMessageProcessor(onlineRenderer),
                    ServerMessage.parser());

            // initializing MessageWriter Object which writes client messages to server
            messageWriter = new MessageWriter<ClientMessage>(this.socket);
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

    public int getClientHashCode() {
        return userHash;
    }

    public OnlineRenderer getOnlineRenderer() {
        return onlineRenderer;
    }
}