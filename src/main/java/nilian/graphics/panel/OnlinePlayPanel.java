package nilian.graphics.panel;

import nilian.graphics.window.LoadingView;
import nilian.mode.GameMode;
import nilian.online.InitialManager;
import nilian.online.OnlineMode;
import nilian.graphics.window.GameWindow;
import nilian.graphics.window.MainWindow;
import nilian.graphics.window.WindowEntity;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class OnlinePlayPanel extends MenuPanel{

    private static JTextField playerNameField;
    private static JTextField serverIpField;
    private static JTextField serverPortField;
    private static JTextField passwordField;
    private static JButton hostServerButton;
    private static JButton joinServerButton;
    private static JButton goBackButton;

    private static MenuPanel resultPanel;
    private static InitialManager initialManager;

    public OnlinePlayPanel(String fileName) {
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

    private static void hostServer() {
        // trying to make the server
        Properties props = getSelectedProps();

        LoadingView loadingView = new LoadingView();
        loadingView.show("Making server ...");
        initialManager = new InitialManager(OnlineMode.host, props);

        loadingView.changeMessage("starting the server ...");
        initialManager.startServer();
        loadingView.changeMessage("Server is up ;)");
        loadingView.hide();

        MainWindow.dispose();
        GameWindow.show(GameMode.online, props);
    }

    private static void joinServer() {
        // trying to join the server
        Properties props = getSelectedProps();

        LoadingView loadingView = new LoadingView();
        loadingView.show("Making the Client ...");
        initialManager = new InitialManager(OnlineMode.joiner, props);
        loadingView.changeMessage("Joining to server "+props.get("server.ip")+":"+props.get("server.port")+" ...");
        boolean connected = initialManager.connectToServer();
        if(connected) {
            loadingView.changeMessage("Connected to server ;)");
            loadingView.changeMessage("Start Listening to server ...");
            initialManager.startListeningToServer();
        } else {
            loadingView.changeMessage("failed to connect to server ;)");
        }
        loadingView.changeMessage("Done !");
        loadingView.hide();

        MainWindow.dispose();
        GameWindow.show(GameMode.online, props);
    }

    private static void goBack() {
        MainWindow.switchPanel(OfflinePlayPanel.getPanel());
    }

    private static Properties getSelectedProps(){
        Properties props = new Properties();
        props.setProperty("server.ip", serverIpField.getText());
        props.setProperty("server.port", serverPortField.getText());
        props.setProperty("server.password", passwordField.getText());
        props.setProperty("player.name", playerNameField.getText());

        return props;
    }
}