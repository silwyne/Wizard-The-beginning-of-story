package nilian.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {

    static JFrame mainWindow ;
    static JPanel panel ;
    static JTextField playerNameF;
    static JButton playB;

    public static void show(){
        mainWindow = new JFrame() ;
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setTitle("Wizard: The Beginning Of Story");
        mainWindow.setSize(new Dimension(300, 300));
        mainWindow.setLocationRelativeTo(null);


        panel = new JPanel();

        //player name field
        playerNameF = new JTextField();
        playerNameF.setSize(new Dimension(20, 50));
        playerNameF.setText("!~player~!");
        panel.add(playerNameF);

        //play button
        playB = new JButton("play");
        playB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameF.getText() ;
                GameWindow.show(playerName);
                mainWindow.dispose();
            }
        });
        panel.add(playB);

        mainWindow.add(panel);
        mainWindow.setVisible(true);
    }
}
