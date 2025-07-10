package nilian.online;

import nilian.online.message.ClientMessage;
import nilian.online.message.ClientMessageType;
import nilian.online.message.GameConfigMessage;
import nilian.online.message.GameModeType;
import nilian.online.message.ServerMessage;
import nilian.online.message.ServerMessageType;

public class MessageParser {

    /**
     * Parses ClientMessage into ServerMessage
     * @param message from Client
     * @return ServerMessage
     */
    public static ServerMessage parse(ClientMessage message) {
        ServerMessage serverMessage ;

        // if player enters the game
        if(message.getType().equals(ClientMessageType.CLIENT_MESSAGE_TYPE_INTRODUCE)) {
            serverMessage = ServerMessage.newBuilder()
                    .setGameConfig(getGameConfigMessage())
                    .setPlayer(message.getPlayerInfo())
                    .setType(ServerMessageType.SERVER_MESSAGE_TYPE_WELCOME).build();
        }

        // if player moved
        else {
            serverMessage = ServerMessage.newBuilder()
                    .setGameConfig(getGameConfigMessage())
                    .setPlayer(message.getPlayerInfo())
                    .setType(ServerMessageType.SERVER_MESSAGE_TYPE_PLAYER).build();
        }
        return serverMessage;
    }


    /**
     * @return game configurations in a message
     */
    private static GameConfigMessage getGameConfigMessage() {
        return GameConfigMessage.newBuilder()
                .setMap("city")
                .setMode(GameModeType.GAME_MODE_TYPE_FREE_FOR_ALL)
                .setTheme("Rainy")
                .setTimestamp(System.currentTimeMillis()).build();
    }
}
