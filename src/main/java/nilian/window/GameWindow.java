package nilian.window;

import nilian.main.GamePanel;

import javax.swing.*;

public class GameWindow {

    static JFrame gameWindow;


    public static void show(String playerName) {
        gameWindow = new JFrame() ;
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Wizard: The Beginning Of Story");

        GamePanel gamePanel = new GamePanel(playerName) ;
        gameWindow.add(gamePanel) ;
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);

        gameWindow.setVisible(true);
        gamePanel.startGameThread();
    }
}
