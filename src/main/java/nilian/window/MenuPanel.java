package nilian.window;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MenuPanel extends JPanel {

    private final Image backgroundImage;

    public MenuPanel(String fileName) {
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