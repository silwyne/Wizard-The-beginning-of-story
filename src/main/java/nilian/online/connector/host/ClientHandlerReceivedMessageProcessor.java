package nilian.online.connector.host;

import com.google.protobuf.Message;
import nilian.online.connector.message.MessageProcessor;
import nilian.online.connector.message.MessageWriter;
import nilian.online.message.*;

import java.util.ArrayList;

public class ClientHandlerReceivedMessageProcessor implements MessageProcessor {

    private final ArrayList<ClientHandler> allOtherClients;
    private final MessageWriter<ServerMessage> messageWriter;
    private final int clientHashCode;

    public ClientHandlerReceivedMessageProcessor(ArrayList<ClientHandler> allClients, MessageWriter<ServerMessage> messageWriter, int clientHashCode) {
        this.messageWriter = messageWriter;
        this.allOtherClients = allClients;
        this.clientHashCode = clientHashCode;
    }

    /**
     * processes the message from one client and
     * decides to broadcast it or something else!
     * @param message message received
     */
    @Override
    public void process(Message message) {
        System.out.println(message);
        broadCast(message);
    }

    /**
     * Broadcasts message to all other clients!
     * @param message message from one client
     */
    private void broadCast(Message message) {
        GameConfigMessage gameConfigMessage = GameConfigMessage.newBuilder()
                .setMap("city")
                .setMode(GameModeType.GAME_MODE_TYPE_FREE_FOR_ALL)
                .setTheme("Rainy")
                .setTimestamp(System.currentTimeMillis()).build();

        ServerMessage serverMessage = ServerMessage.newBuilder()
                .setGameConfig(gameConfigMessage)
                .setPlayer(((ClientMessage) message).getPlayerInfo())
                .setType(ServerMessageType.SERVER_MESSAGE_TYPE_WELCOME).build();

        for(ClientHandler client : allOtherClients)
        {
            if(client != null) {
                if(client.clientHashcode != this.clientHashCode) {
                    messageWriter.send(serverMessage);
                }
            }
        }
    }

}
