package nilian.graphics.window;

import nilian.game.panel.StoryModePanel;
import nilian.game.GameMode;
import nilian.game.panel.OnlineGamePanel;
import nilian.online.connector.joiner.GameClient;

import javax.swing.*;
import java.util.Properties;

public class GameWindow {


    public static void show(GameMode gameMode, Properties props, GameClient gameClient){

        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle(WindowEntity.WINDOW_TITLE);

        if(gameMode.equals(GameMode.offline)) {
            StoryModePanel offlinePanel = new StoryModePanel(props, gameClient);
            gameWindow.add(offlinePanel);
            offlinePanel.startGameThread();
        } else {
            OnlineGamePanel onlinePanel = new OnlineGamePanel(props, gameClient);
            gameWindow.add(onlinePanel);
            onlinePanel.startGameThread();
        }

        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

    }

}
