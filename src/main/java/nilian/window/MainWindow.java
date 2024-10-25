package nilian.window;

import nilian.mode.GameMode;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Properties;

public class MainWindow {

    static JFrame mainWindow;
    static BackPanel panel;
    static JTextField playerNameF;
    static JButton playB;
    static JComboBox<GameMode> gameMode;

    public static void show() {
        mainWindow = new JFrame();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setTitle("Wizard: The Beginning Of Story");

        panel = new BackPanel("/images/dragon.jpg");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // player name field
        playerNameF = new JTextField("!~player~!", 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(playerNameF, gbc);

        // Game mode selection
        gameMode = new JComboBox<>(GameMode.values());
        gameMode.setSelectedItem(GameMode.offline);
        gbc.gridy = 1;
        panel.add(gameMode, gbc);

        // play button
        playB = new JButton("Play");
        playB.addActionListener((i) -> {

            // preparing the properties
            Properties props = new Properties();
            props.setProperty("player.name", playerNameF.getText());
            props.setProperty("game.mode", Objects.requireNonNull(gameMode.getSelectedItem()).toString());

            GameWindow.show(props);

            mainWindow.dispose();
            });
        gbc.gridy = 2;
        panel.add(playB, gbc);

        mainWindow.setContentPane(panel);
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

    static class BackPanel extends JPanel {
        private static Image backgroundImage;

        public BackPanel(String fileName) {
            backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(fileName))).getImage();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 300); // Set your preferred size here
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }
}