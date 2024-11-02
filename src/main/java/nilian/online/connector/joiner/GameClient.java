package nilian.online.connector.joiner;

import nilian.online.connector.message.MessageListener;
import nilian.online.connector.message.MessageWriter;
import nilian.online.message.*;

import java.io.*;
import java.net.Socket;

public class GameClient {

    private Socket socket;
    private final String serverIP;
    private final int serverPort;

    private final String username ;
    private final int userHash ;

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
                    new ClientMessageProcessor(),
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

    public void introduceToServer() {
        if(introductionMessage == null){
            introductionMessage = ClientMessage.newBuilder()
                    .setTimestamp(System.currentTimeMillis())
                    .setType(ClientMessageType.CLIENT_MESSAGE_TYPE_INTRODUCE)
                    .setPlayerInfo(getPlayerMessage())
                    .build();
        }
        messageWriter.send(introductionMessage);
    }

    public PlayerMessage getPlayerMessage() {
        if(playerMessage == null) {
            playerMessage = PlayerMessage.newBuilder()
                    .setPlayerHash(userHash)
                    .setName(username)
                    .setTimestamp(System.currentTimeMillis())
                    .setType(PlayerMessageType.PLAYER_MESSAGE_TYPE_SPAWN)
                    .setTeamCode(0)
                    .setSuitCode(0)
                    .build();
        }
        return playerMessage;
    }
}