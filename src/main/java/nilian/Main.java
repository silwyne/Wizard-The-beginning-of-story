package nilian;

import nilian.main.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jf = new JFrame() ;
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setTitle("Wizard: The Beginning Of Story");
        GamePanel gamePanel = new GamePanel() ;
        jf.add(gamePanel) ;
        jf.pack();
        jf.setLocationRelativeTo(null);

        jf.setVisible(true);
        gamePanel.startGameThread();
    }
}