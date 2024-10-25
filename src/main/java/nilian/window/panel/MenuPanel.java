package nilian.window.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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

    /**
     * @param text default text of field
     * @param columns empty space of field
     * @return simple JTextField
     */
    protected static JTextField createTextField(String text, int columns) {
        JTextField textField = new JTextField(text, columns);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    /**
     * simply creates button
     * @param text button name
     * @param listener action of button
     * @return created button
     */
    protected static JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    /**
     *
     * @param panel panel to add component to
     * @param component component to add to panel
     * @param gbc the GridBagConstraints panel is using
     * @param x column x
     * @param y row y
     */
    protected static void addComponent(JPanel panel, Component component, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(component, gbc);
    }

    /**
     * @param s label name
     * @return label with custom font and color!
     */
    protected static JLabel getNameLabel(String s) {
        JLabel label = new JLabel(s);
        // Use a custom font
        Font customFont = new Font("Segoe UI", Font.BOLD, 16);
        // Apply the custom font
        label.setFont(customFont);
        // Set foreground color
        label.setForeground(new Color(0, 255, 102));
        // Add a subtle shadow effect
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(2, 2, 2, 2),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                )
        ));

        return label;
    }
}