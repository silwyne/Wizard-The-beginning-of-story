package nilian.graphics.window;

import nilian.mains.OfflineGamePanel;
import nilian.mains.GameMode;
import nilian.mains.OnlineGamePanel;

import javax.swing.*;
import java.util.Properties;

public class GameWindow {


    public static void show(GameMode gameMode, Properties props){

        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle(WindowEntity.WINDOW_TITLE);

        if(gameMode.equals(GameMode.offline)) {
            OfflineGamePanel offlinePanel = new OfflineGamePanel(props);
            gameWindow.add(offlinePanel);
            offlinePanel.startGameThread();
        } else {
            OnlineGamePanel onlinePanel = new OnlineGamePanel(props);
            gameWindow.add(onlinePanel);
            // onlinePanel.startGameThread();
        }

        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

    }

}
