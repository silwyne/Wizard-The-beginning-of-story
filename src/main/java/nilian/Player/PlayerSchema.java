package nilian.Player;

/**
 * Holds Info about how player looks!
 */
public class PlayerSchema {

    private String playerName;
    private int clientHashCode;
    private int playerX;
    private int playerY;

    public PlayerSchema(String playerName, int clientHashCode, int playerX, int playerY) {
        this.playerName = playerName;
        this.clientHashCode = clientHashCode;
        this.playerX = playerX;
        this.playerY = playerY;
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
}