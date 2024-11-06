package nilian.Player;

import java.awt.*;

/**
 * Holds Info about how player looks!
 */
public class PlayerSchema {

    private String playerName;
    private Color playerColor;
    private int clientHashCode;
    private int playerX;
    private int playerY;
    private int playerSize;

    public PlayerSchema(String playerName, int clientHashCode, int playerX, int playerY, Color playerColor, int playerSize) {
        this.playerName = playerName;
        this.playerColor = playerColor;
        this.clientHashCode = clientHashCode;
        this.playerX = playerX;
        this.playerY = playerY;
        this.playerSize = playerSize;
    }

    public int getPlayerSize() {
        return playerSize;
    }

    public void setPlayerSize(int playerSize) {
        this.playerSize = playerSize;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getClientHashCode() {
        return clientHashCode;
    }

    public void setClientHashCode(int clientHashCode) {
        this.clientHashCode = clientHashCode;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }
}