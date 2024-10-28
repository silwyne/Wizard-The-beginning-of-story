package nilian.graphics.window;

import nilian.graphics.panel.GamePanel;
import nilian.mode.GameMode;

import javax.swing.*;
import java.util.Properties;

public class GameWindow {

    private static JFrame gameWindow;
    private static GamePanel gamePanel ;


    public static void show(GameMode gameMode, Properties props){

        gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle(WindowEntity.WINDOW_TITLE);

        gamePanel = new GamePanel(gameMode, props);

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
