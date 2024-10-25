package nilian.window;

import nilian.window.panel.OfflinePlayPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private static JFrame menuWindow;

    public static void show() {

        menuWindow = new JFrame();
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setResizable(false);
        menuWindow.setTitle(WindowEntity.WINDOW_TITLE);

        menuWindow.add(OfflinePlayPanel.getPanel());
        menuWindow.pack();
        menuWindow.setLocationRelativeTo(null);
        menuWindow.setVisible(true);
    }

    public static void dispose(){
        menuWindow.dispose();
    }

    /**
     * Simply switches the Panel for changing windows
     * @param newPanel new panel to show
     */
    public static void switchPanel(JPanel newPanel) {
        // Remove the current panel
        Container contentPane = menuWindow.getContentPane();
        contentPane.removeAll();

        // Add the new panel
        contentPane.add(newPanel);

        // Refresh the menuWindow
        menuWindow.revalidate();
        menuWindow.repaint();
    }


    public static JFrame getMenuWindow() {
        return menuWindow;
    }
}