package nilian.graphics.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingView {
    private final int ICON_SIZE = 30;
    private final int ANIMATION_DELAY = 100;

    private JLabel messageLabel;
    private JDialog dialog;
    private Timer timer;

    public void show(String message) {
        if (SwingUtilities.isEventDispatchThread()) {
            createAndShowDialog(message);
        } else {
            try {
                SwingUtilities.invokeAndWait(() -> createAndShowDialog(message));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createAndShowDialog(String message) {
        JPanel panel = new JPanel(new BorderLayout());
        messageLabel = new JLabel(message);
        JLabel iconLabel = new JLabel();
        panel.add(messageLabel, BorderLayout.CENTER);
        panel.add(iconLabel, BorderLayout.WEST);

        dialog = new JDialog((Frame) null, "Loading", false);
        dialog.setUndecorated(true);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        timer = new Timer(ANIMATION_DELAY, new ActionListener() {
            private int angle = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                angle = (angle + 30) % 360;
                iconLabel.setIcon(createSpinnerIcon(angle));
            }
        });

        timer.start();
        dialog.setVisible(true);
    }

    public void hide() {
        SwingUtilities.invokeLater(() -> {
            if (timer != null) {
                timer.stop();
            }
            if (dialog != null) {
                dialog.dispose();
            }
        });
    }

    private Icon createSpinnerIcon(int angle) {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.BLUE);
                g2d.rotate(Math.toRadians(angle), x + ICON_SIZE / 2, y + ICON_SIZE / 2);
                g2d.drawArc(x, y, ICON_SIZE, ICON_SIZE, 0, 270);
                g2d.dispose();
            }

            @Override
            public int getIconWidth() {
                return ICON_SIZE;
            }

            @Override
            public int getIconHeight() {
                return ICON_SIZE;
            }
        };
    }

    public void changeMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            if (messageLabel != null) {
                messageLabel.setText(message);
            }
        });
    }
}