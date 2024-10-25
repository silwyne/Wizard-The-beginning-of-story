package nilian.window;

import nilian.main.GamePanel;

import javax.swing.*;
import java.util.Properties;

public class GameWindow {

    private static JFrame gameWindow;
    private static GamePanel gamePanel ;


    public static void show(Properties props){

        gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle(WindowEntity.WINDOW_TITLE);

        gamePanel = new GamePanel(props);

        gameWindow.add(gamePanel);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

        gamePanel.startGameThread();
    }

    public static void dispose() {
        gameWindow.dispose();
    }

    public static GamePanel getGamePanel() {
        return gamePanel;
    }
}
