package nilian.graphics.panel;

import nilian.graphics.window.LoadingWindow;
import nilian.game.GameMode;
import nilian.online.ConnectionInitializer;
import nilian.online.OnlineMode;
import nilian.graphics.window.GameWindow;
import nilian.graphics.window.MainWindow;
import nilian.graphics.window.WindowEntity;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class OnlineMenuPanel extends MenuPanel{

    private static JTextField playerNameField;
    private static JTextField serverIpField;
    private static JTextField serverPortField;
    private static JTextField passwordField;
    private static JButton hostServerButton;
    private static JButton joinServerButton;
    private static JButton goBackButton;

    private static MenuPanel resultPanel;
    private static ConnectionInitializer connectionInitializer;

    public OnlineMenuPanel(String fileName) {
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
        playerNameField = createTextField("player_1", WindowEntity.TEXT_FIELD_COLUMNS);
        serverIpField = createTextField("localhost", WindowEntity.TEXT_FIELD_COLUMNS);
        serverPortField = createTextField("8696", WindowEntity.TEXT_FIELD_COLUMNS);
        passwordField = createTextField("defaultPass", WindowEntity.TEXT_FIELD_COLUMNS);

        // buttons
        hostServerButton = createButton("Host server", e -> hostServer());
        joinServerButton = createButton("Join server", e -> joinServer());
        goBackButton = createButton("Back", e -> goBack());
    }

    /**
     * @param panel the panel that is going to get components
     */
    private static void addComponentsToPanel(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // fields name
        addComponent(panel, getNameLabel("player name"), gbc, 0, 0);
        addComponent(panel, getNameLabel("server ip"), gbc, 0,1);
        addComponent(panel, getNameLabel("server port"), gbc, 0,2);
        addComponent(panel, getNameLabel("password"), gbc, 0,3);

        // text fields
        addComponent(panel, playerNameField, gbc, 1, 0);
        addComponent(panel, serverIpField, gbc, 1,1);
        addComponent(panel, serverPortField, gbc, 1,2);
        addComponent(panel, passwordField, gbc, 1,3);

        // buttons
        gbc.fill = GridBagConstraints.VERTICAL;
        addComponent(panel, hostServerButton, gbc, 0, 4);
        addComponent(panel, joinServerButton, gbc, 1, 4);
        addComponent(panel, goBackButton, gbc, 2, 4);
    }


    /**
     * @param text default text of field
     * @param columns empty space of field
     * @return simple JTextField
     */
    public static JTextField createTextField(String text, int columns) {
        JTextField textField = new JTextField(text, columns);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    /**
     * Does all the stuff needed to Create a Game server and then join it!
     */
    private static void hostServer() {
        SwingUtilities.invokeLater(() -> {
            Properties props = getSelectedProps();
            JFrame parentFrame = MainWindow.getMenuWindow();
            LoadingWindow loadingWindow = new LoadingWindow(parentFrame, "Making the Server ...");
            loadingWindow.setVisible(true);

            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                connectionInitializer = new ConnectionInitializer(OnlineMode.host, props);

                loadingWindow.setMessage("setting up the server ...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                connectionInitializer.setUpServer();

                loadingWindow.setMessage("setting up the client ...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                boolean connected = connectionInitializer.setUpClient();
                if(connected) {
                    loadingWindow.setMessage("connected successfully ...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    loadingWindow.dispose();
                    // Finally setting up the Game
                    setUpGame();
                } else {
                    loadingWindow.setMessage("Failed to connect the server ...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    loadingWindow.dispose();
                }

            }).start();
        });
    }

    /**
     * Does all the stuff needed to join the game server
     */
    private static void joinServer() {
        SwingUtilities.invokeLater(() -> {
            Properties props = getSelectedProps();
            JFrame parentFrame = MainWindow.getMenuWindow();
            LoadingWindow loadingWindow = new LoadingWindow(parentFrame, "Making the Client ...");
            loadingWindow.setVisible(true);

            new Thread(() -> {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                connectionInitializer = new ConnectionInitializer(OnlineMode.joiner, props);

                loadingWindow.setMessage("Setting up the client ...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                boolean connected = connectionInitializer.setUpClient();
                if(connected){
                    loadingWindow.setMessage("connected successfully ...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    loadingWindow.dispose();
                    // Finally setting up the Game
                    setUpGame();
                } else {
                    loadingWindow.setMessage("Failed to connect the server ...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    loadingWindow.dispose();
                }
            }).start();
        });
    }


    /**
     * Button which goes back page in menu
     */
    private static void goBack() {
        MainWindow.switchPanel(OfflineMenuPanel.getPanel());
    }

    /**
     * extracts all the information into a Properties.Object
     * @return Server or Connection Properties
     */
    private static Properties getSelectedProps(){
        Properties props = new Properties();
        props.setProperty("server.ip", serverIpField.getText());
        props.setProperty("server.port", serverPortField.getText());
        props.setProperty("server.password", passwordField.getText());
        props.setProperty("player.name", playerNameField.getText());

        return props;
    }

    /**
     * Goes into Game Window
     */
    private static void setUpGame() {
        MainWindow.dispose();
        GameWindow.show(GameMode.online, getSelectedProps(), connectionInitializer.getGameClient());
    }
}