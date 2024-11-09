package nilian.Player;

import java.awt.*;

/**
 * Holds Info about how player looks!
 */
public class PlayerSchema {

    private String playerName;
    private Color playerColor;
    private String suitName;
    private PlayerState playerState;
    private int clientHashCode;
    private int playerX;
    private int playerY;
    private int playerSize;

    public PlayerSchema(String playerName, int clientHashCode, int playerX, int playerY, Color playerColor, int playerSize, PlayerState playerState, String suitName) {
        this.playerName = playerName;
        this.playerColor = playerColor;
        this.playerState = playerState;
        this.clientHashCode = clientHashCode;
        this.playerX = playerX;
        this.playerY = playerY;
        this.playerSize = playerSize;
        this.suitName = suitName;
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

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public String getSuitName() {
        return suitName;
    }

    public void setSuitName(String suitName) {
        this.suitName = suitName;
    }
}