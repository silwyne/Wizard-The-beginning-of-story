package nilian.graphics.panel;

import nilian.game.GameMode;
import nilian.graphics.window.GameWindow;
import nilian.graphics.window.MainWindow;
import nilian.graphics.window.WindowEntity;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class OfflineMenuPanel extends MenuPanel{

    private static JTextField playerNameF;
    private static JButton playOnline;
    private static JButton playOffline;

    private static MenuPanel resultPanel;

    public OfflineMenuPanel(String fileName) {
        super(fileName);
    }

    public static MenuPanel getPanel() {
        if(resultPanel == null) {

            MenuPanel panel = new MenuPanel(WindowEntity.BACKGROUND_IMAGE_PATH);
            panel.setLayout(new GridBagLayout());

            initializeComponents();
            addComponentsToPanel(panel);

            resultPanel = panel ;
            return resultPanel;
        }
        return resultPanel;
    }

    /**
     * initializes components of panel
     */
    private static void initializeComponents() {
        // fields
        playerNameF = createTextField("player_1", WindowEntity.TEXT_FIELD_COLUMNS);

        // buttons
        playOffline = createButton("play offline", e -> playOfflineAction());
        playOnline = createButton("play online", e -> playOnlineAction());
    }


    /**
     * This starts the game locally offline
     */
    private static void playOfflineAction(){
        // preparing the properties
        Properties props = new Properties();
        props.setProperty("player.name", playerNameF.getText());
        MainWindow.dispose();
        GameWindow.show(GameMode.offline, props, null);
    }


    private static void playOnlineAction() {
        MainWindow.switchPanel(OnlineMenuPanel.getPanel());
    }


    public static void addComponentsToPanel(JPanel panel){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Fields
        addComponent(panel, getNameLabel("player name"), gbc, 0, 0);
        addComponent(panel, playerNameF, gbc, 1, 0);

        gbc.fill = GridBagConstraints.VERTICAL;
        // Button
        addComponent(panel, playOffline, gbc, 0, 1);
        addComponent(panel, playOnline, gbc, 0, 2);
    }
}
