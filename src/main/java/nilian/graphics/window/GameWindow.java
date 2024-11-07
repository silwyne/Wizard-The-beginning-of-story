package nilian.graphics.window;

import nilian.gamePanel.OnlineGamePanel;
import nilian.online.connector.joiner.GameClient;

import javax.swing.*;
import java.util.Properties;

public class GameWindow {


    public static void show(Properties props, GameClient gameClient){

        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle(WindowEntity.WINDOW_TITLE);

        OnlineGamePanel onlinePanel = new OnlineGamePanel(props, gameClient);
        gameWindow.add(onlinePanel);
        onlinePanel.startGameThread();

        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

    }

}
