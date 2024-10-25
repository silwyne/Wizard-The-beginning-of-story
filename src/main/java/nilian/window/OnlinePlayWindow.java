package nilian.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OnlinePlayWindow {
    private static final String BACKGROUND_IMAGE_PATH = "/images/dragon.jpg";
    private static final String WINDOW_TITLE = "Wizard: The Beginning Of Story";
    private static final int TEXT_FIELD_COLUMNS = 20;

    private JFrame menuWindow;
    private JTextField playerNameField;
    private JTextField serverIpField;
    private JTextField serverPortField;
    private JTextField passwordField;
    private JButton hostServerButton;
    private JButton joinServerButton;
    private JButton goBackButton;

    public static void show() {
        SwingUtilities.invokeLater(() -> new OnlinePlayWindow().createAndShowGUI());
    }

    private void createAndShowGUI() {
        menuWindow = new JFrame(WINDOW_TITLE);
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setResizable(false);

        MenuPanel panel = new MenuPanel(BACKGROUND_IMAGE_PATH);
        panel.setLayout(new GridBagLayout());

        initializeComponents();
        addComponentsToPanel(panel);

        menuWindow.setContentPane(panel);
        menuWindow.pack();
        menuWindow.setLocationRelativeTo(null);
        menuWindow.setVisible(true);
    }

    private void initializeComponents() {
        playerNameField = createTextField("player_1", TEXT_FIELD_COLUMNS);
        serverIpField = createTextField("localhost", TEXT_FIELD_COLUMNS);
        serverPortField = createTextField("8696", 5);
        passwordField = createTextField("defaultPass", TEXT_FIELD_COLUMNS);

        hostServerButton = createButton("Host server", e -> hostServer());
        joinServerButton = createButton("Join server", e -> joinServer());
        goBackButton = createButton("Back", e -> goBack());
    }

    private void addComponentsToPanel(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(panel, playerNameField, gbc, 0, 0);
        addComponent(panel, serverIpField, gbc, 0, 1);
        addComponent(panel, serverPortField, gbc, 0, 2);
        addComponent(panel, passwordField, gbc, 0, 3);

        gbc.fill = GridBagConstraints.VERTICAL;
        addComponent(panel, hostServerButton, gbc, 0, 4);
        addComponent(panel, joinServerButton, gbc, 0, 5);
        addComponent(panel, goBackButton, gbc, 0, 6);
    }

    private JTextField createTextField(String text, int columns) {
        JTextField textField = new JTextField(text, columns);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    private void addComponent(JPanel panel, Component component, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(component, gbc);
    }

    private void hostServer() {
        // TODO: Implement host server functionality
        JOptionPane.showMessageDialog(menuWindow, "Host server functionality not implemented yet.");
    }

    private void joinServer() {
        // TODO: Implement join server functionality
        JOptionPane.showMessageDialog(menuWindow, "Join server functionality not implemented yet.");
    }

    private void goBack() {
        menuWindow.dispose();
        MainWindow.show();
    }
}