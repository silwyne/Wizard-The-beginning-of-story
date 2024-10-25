package nilian.window;

import nilian.main.GamePanel;
import nilian.mode.GameMode;

import javax.swing.*;

public class GameWindow {

    static JFrame gameWindow;


    public static void show(String playerName, GameMode gameMode) {
        gameWindow = new JFrame() ;
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Wizard: The Beginning Of Story");

        GamePanel gamePanel = new GamePanel(playerName) ;
        gameWindow.add(gamePanel) ;
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);

        //This is for finally making the game full screen
//        gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameWindow.setVisible(true);
        gamePanel.startGameThread();
    }
}
