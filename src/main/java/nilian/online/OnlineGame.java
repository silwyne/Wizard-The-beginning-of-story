package nilian.online;

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

    public OnlineGame(OnlineMode onlineMode, Properties props) {
        this.onlineMode = onlineMode;
        this.props = props;
    }
}
