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
public class OnlineGame {

    private final OnlineMode onlineMode;
    private final Properties props;
    private GameServer gameServer;
    private Thread serverListener ;
    private GameClient gameClient;

    public OnlineGame(OnlineMode onlineMode, Properties props) {
        // saving configurations
        this.onlineMode = onlineMode;
        this.props = props;

        setUp();
    }

    private void setUp() {
        // handling Server Connection mode
        if(onlineMode.equals(OnlineMode.host)) {
            // building up the server
            this.gameServer = new GameServer(Integer.parseInt(props.get("server.port").toString()));
            // starting the server
            serverListener = new Thread(this.gameServer::startServer);
            serverListener.start();

        // handling Joiner Connection mode
        } else {
            // making the client to join the server
            this.gameClient = new GameClient(
                    props.get("player.name").toString(),
                    props.get("server.ip").toString(),
                    Integer.parseInt(props.get("server.port").toString()));

            // first try to connect
            try {
                this.gameClient.connect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // starting client listener
            // listen for incoming messages
            this.gameClient.listenForMessage();
        }
    }
}
