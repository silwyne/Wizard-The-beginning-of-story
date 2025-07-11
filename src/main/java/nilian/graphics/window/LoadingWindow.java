package nilian.graphics.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class LoadingWindow extends JFrame {
    private JLabel messageLabel;
    private JProgressBar progressBar;

    public LoadingWindow(JFrame parent, String message) {
        setUndecorated(true);
        setSize(250, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(messageLabel, BorderLayout.NORTH);

        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        panel.add(progressBar, BorderLayout.CENTER);

        add(panel);
        setLocationRelativeTo(parent);
        setAlwaysOnTop(true);
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    public void setOperation(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }

}