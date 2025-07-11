package nilian.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Random;

import nilian.gamepanel.GamePanel;
import nilian.online.message.PlayerMessage;
import nilian.online.message.PlayerMessageType;
import nilian.player.suit.PlayerFrameProvider;

public class Player extends PlayerEntity {

	private final PlayerSchema playerSchema;
	private final PlayerUpdater playerUpdater;
	private final String nameColor;

	public Player(GamePanel gamePanel, String playerName, String suitName)  {
		playerSize = gamePanel.tileSize;

		int playerHash = gamePanel.getGameClient().getClientHashCode();

		// getting some random color
		Color color = getRandomColor();
		nameColor = color.getRed() + "," + color.getGreen() + "," + color.getBlue();

		this.playerSchema = new PlayerSchema(
				playerName,
				playerHash,
				gamePanel.screenHeight / 2,
				gamePanel.screenHeight / 2,
				color,
				playerSize,
				PlayerState.IDLE,
				suitName,
				PlayerOrientation.RIGHT);

		playerSchema.setPlayerState(PlayerState.IDLE);
		MovementHandler movementHandler = new MovementHandler(this.playerSchema, gamePanel);
		playerUpdater = new PlayerUpdater(
				new PlayerFrameProvider(suitName),
				this.playerSchema,
				movementHandler,
				gamePanel.getKeyH());
	}

	public boolean update() {
		return playerUpdater.update();
	}

	public void draw(Graphics2D g2) {

		g2.drawImage(playerUpdater.getPlayerFrameImage(), playerSchema.getPlayerX(), playerSchema.getPlayerY(),
				playerSize, playerSize, null);

		drawPlayerName(g2, playerSchema);
	}

	public static Color getRandomColor() {
		Random random = new Random();
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		return new Color(r, g, b);
	}

	public PlayerMessage getPlayerMessage() {
		return PlayerMessage.newBuilder()
				.setNameColor(nameColor)
				.setType(PlayerMessageType.PLAYER_MESSAGE_TYPE_MOVE)
				.setPlayerHash(playerSchema.getClientHashCode())
				.setSuitCode(playerSchema.getSuitName())
				.setState(playerSchema.getPlayerState().toString())
				.setOrientation(playerSchema.getPlayerOrientation().toString())
				.setName(playerSchema.getPlayerName())
				.setTimestamp(System.currentTimeMillis())
				.setX(playerSchema.getPlayerX())
				.setY(playerSchema.getPlayerY())
				.build();
	}

	public static void drawPlayerName(Graphics2D g2, PlayerSchema schema) {
		g2.setFont(new Font("Arial", Font.BOLD, 12));
		g2.setColor(schema.getPlayerColor());

		FontMetrics fm = g2.getFontMetrics();
		int textWidth = fm.stringWidth(schema.getPlayerName());
		int textX = schema.getPlayerX() + (schema.getPlayerSize() / 2) - (textWidth / 2);
		int textY = schema.getPlayerY() - 2;

		g2.drawString(schema.getPlayerName(), textX, textY);
	}

}
