package nilian;

import nilian.main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame() ;
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setTitle("Wizard: The Beginning Of Story");
        mainWindow.setSize(new Dimension(300, 300));
        mainWindow.setLocationRelativeTo(null);


        JPanel panel = new JPanel();

        //player name field
        JTextField playerNameF = new JTextField();
        playerNameF.setText("!~player~!");
        panel.add(playerNameF);

        //play button
        JButton playB = new JButton("play");
        playB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameF.getText() ;
                playTheGame(playerName);
                mainWindow.dispose();
            }
        });
        panel.add(playB);



        mainWindow.add(panel);

        mainWindow.setVisible(true);
    }

    private static void playTheGame(String playerName){
        JFrame gameWindow = new JFrame() ;
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