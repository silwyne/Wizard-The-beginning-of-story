package nilian.online;

import nilian.online.connector.host.GameServer;
import nilian.online.connector.joiner.GameClient;

import java.io.IOException;
import java.util.Properties;

/**
 * Handles all sending and other stuff!
 * Like when other players move or do something this handles the change in game.
 *
 * @author seyed mohamad hasan tabatabaei
 */
public class ConnectionHandler {

    private final OnlineMode onlineMode;
    private final Properties props;
    private GameServer gameServer;
    private Thread serverListener ;
    private GameClient gameClient;

    public ConnectionHandler(OnlineMode onlineMode, Properties props) {
        // saving configurations
        this.onlineMode = onlineMode;
        this.props = props;

        initialSetUp();
    }

    /**
     * Based on Joiner or Host mode you choose this sets up Objects needed
     * GameClient or GameServer
     */
    private void initialSetUp() {
        // handling Server Connection mode
        if(onlineMode.equals(OnlineMode.host)) {
            // building up the server
            this.gameServer = new GameServer(Integer.parseInt(props.get("server.port").toString()));
        }
        // making the client to join the server
        this.gameClient = new GameClient(
                props.get("player.name").toString(),
                props.get("server.ip").toString(),
                Integer.parseInt(props.get("server.port").toString()));
    }


    public void setUpServer() {
        // step 1
        // starting the server
        serverListener = new Thread(this.gameServer::startServer);
        serverListener.start();
    }

    public boolean setUpClient() {
        // first try to connect to server
        boolean connected ;
        try {
            this.gameClient.connect();
            connected = true ;
        } catch (IOException e) {
            connected = false;
        }
        if(connected){
            // starting client listener
            // listen for incoming messages
            this.gameClient.listenForMessage();
            // introduces the player to server
            this.gameClient.introduceToServer();
            // connected to server successfully
        }
        return connected;
    }

    public GameClient getGameClient(){
        return gameClient;
    }
}
