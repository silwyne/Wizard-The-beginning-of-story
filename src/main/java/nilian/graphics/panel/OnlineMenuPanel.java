package nilian.graphics.panel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import nilian.graphics.window.GameWindow;
import nilian.graphics.window.LoadingWindow;
import nilian.graphics.window.MainWindow;
import nilian.graphics.window.WindowEntity;
import nilian.online.ConnectionHandler;
import nilian.online.OnlineMode;
import nilian.player.suit.SuitHandler;

public class OnlineMenuPanel extends MenuPanel{

    private static JTextField playerNameField;
    private static JTextField serverIpField;
    private static JTextField serverPortField;
    private static JTextField passwordField;
    private static JComboBox<String> playerSuitField;
    private static JButton hostServerButton;
    private static JButton joinServerButton;
    private static JButton goBackButton;

    private static MenuPanel resultPanel;
    private static ConnectionHandler connectionHandler;

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
        playerSuitField = createComboBoxLabel(SuitHandler.getAllSuitNames());


        // buttons
        hostServerButton = createButton("Host server", e -> joinGame(OnlineMode.host));
        joinServerButton = createButton("Join server", e -> joinGame(OnlineMode.joiner));
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
        addComponent(panel, getNameLabel("player suit"), gbc, 0,4);

        // text fields
        addComponent(panel, playerNameField, gbc, 1, 0);
        addComponent(panel, serverIpField, gbc, 1,1);
        addComponent(panel, serverPortField, gbc, 1,2);
        addComponent(panel, passwordField, gbc, 1,3);
        addComponent(panel, playerSuitField, gbc, 1,4);

        // buttons
        gbc.fill = GridBagConstraints.VERTICAL;
        addComponent(panel, hostServerButton, gbc, 0, 5);
        addComponent(panel, joinServerButton, gbc, 1, 5);
        addComponent(panel, goBackButton, gbc, 2, 5);
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

    private static JComboBox<String> createComboBoxLabel(ArrayList<String> options) {
        return new JComboBox<>(options.toArray(new String[0]));
    }

    private static void joinGame(OnlineMode mode) {
        SwingUtilities.invokeLater(() -> {
            Properties props = getSelectedProps();
            JFrame parentFrame = MainWindow.getMenuWindow();
            LoadingWindow loadingWindow = new LoadingWindow(parentFrame, "Making the Client ...");
            loadingWindow.setVisible(true);

            new Thread(() -> {

                connectionHandler = new ConnectionHandler(mode, props);
                loadingWindow.setMessage("Setting up the client ...");

                if(mode.equals(OnlineMode.host)) {
                    connectionHandler.setUpServer();
                }

                connectionHandler.setUpClientAndListenForMessages();
                setUpGameIfConnected(loadingWindow, connectionHandler.isConnected());

            }).start();
        });
    }

    private static void setUpGameIfConnected(LoadingWindow loadingWindow, boolean isConnected) {
        if(isConnected){
            loadingWindow.setMessage("connected successfully ...");
            loadingWindow.dispose();
            setUpGame();
        } else {
            loadingWindow.setMessage("Failed to connect the server ...");
            loadingWindow.dispose();
        }
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
        props.setProperty("player.suit", (String) playerSuitField.getSelectedItem());

        System.out.println(props);
        return props;
    }

    /**
     * Goes into Game Window
     */
    private static void setUpGame() {
        MainWindow.dispose();
        GameWindow.show(getSelectedProps(), connectionHandler.getGameClient());
    }
}