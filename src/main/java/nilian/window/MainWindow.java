package nilian.window;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class MainWindow {


    public static void show() {

        //variables
        JFrame menuWindow;
        MenuPanel panel;
        JTextField playerNameF;
        JButton playOnline;
        JButton playOffline;
        GridBagConstraints gbc;

        menuWindow = new JFrame();
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setResizable(false);
        menuWindow.setTitle("Wizard: The Beginning Of Story");

        panel = new MenuPanel("/images/dragon.jpg");
        panel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // player name field
        playerNameF = new JTextField("player_1", 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(playerNameF, gbc);

        // play offline Button
        playOffline = new JButton("play offline");
        playOffline.addActionListener((i) -> {
            // preparing the properties
            Properties props = new Properties();
            props.setProperty("player.name", playerNameF.getText());
            GameWindow.show(props);
            menuWindow.dispose();
        });
        gbc.gridy = 1;
        panel.add(playOffline, gbc);


        // play online button
        playOnline = new JButton("play online");
        playOnline.addActionListener((i) -> {
            OnlinePlayWindow.show();
            menuWindow.dispose();
        });
        gbc.gridy = 2;
        panel.add(playOnline, gbc);

        menuWindow.setContentPane(panel);
        menuWindow.pack();
        menuWindow.setLocationRelativeTo(null);
        menuWindow.setVisible(true);
    }

}