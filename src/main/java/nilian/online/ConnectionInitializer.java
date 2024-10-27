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
public class ConnectionInitializer {

    private final OnlineMode onlineMode;
    private final Properties props;
    private GameServer gameServer;
    private Thread serverListener ;
    private GameClient gameClient;

    public ConnectionInitializer(OnlineMode onlineMode, Properties props) {
        // saving configurations
        this.onlineMode = onlineMode;
        this.props = props;

        setUp();
    }

    /**
     * Based on Joiner or Host mode you choose this sets up Objects needed
     * GameClient or GameServer
     */
    private void setUp() {
        // handling Server Connection mode
        if(onlineMode.equals(OnlineMode.host)) {
            // building up the server
            this.gameServer = new GameServer(Integer.parseInt(props.get("server.port").toString()));
        // handling Joiner Connection mode
        } else {
            // making the client to join the server
            this.gameClient = new GameClient(
                    props.get("player.name").toString(),
                    props.get("server.ip").toString(),
                    Integer.parseInt(props.get("server.port").toString()));

            System.out.println("Made Client");
        }
    }


    /**
     * starts game server
     */
    public void startServer(){
        // starting the server
        serverListener = new Thread(this.gameServer::startServer);
        serverListener.start();
    }

    /**
     * Connects the client to server if exists or is reachable
     * @return true if connected
     */
    public boolean connectToServer(){
        // first try to connect
        try {
            this.gameClient.connect();
            return true ;
        } catch (IOException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    /**
     * client starts listening to server messages
     * This way client receives other clients messages
     */
    public void startListeningToServer() {
        // starting client listener
        // listen for incoming messages
        this.gameClient.listenForMessage();
    }

    /**
     * INTRODUCES the player to server by informing the username and userHash
     */
    public void introduceToServer() {
        // introduces the player to server
        this.gameClient.introduceToServer();
    }
}
