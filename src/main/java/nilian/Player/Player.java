package nilian.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import nilian.Player.suit.PlayerSuit;
import nilian.Player.suit.SuitHandler;
import nilian.gamePanel.GamePanel;
import nilian.input.KeyHandler;
import nilian.online.message.PlayerMessage;
import nilian.online.message.PlayerMessageType;


/**
 * Player object which includes all stuff a Player object must Contain
 */
public class Player extends PlayerEntity {

	public final PlayerSchema playerSchema ;
	private final MovementHandler movementHandler;

	private final PlayerSuit playerSuit;
	private static final Random random = new Random();

	GamePanel gamePanel;
	KeyHandler key;

	private String nameColor ;

	public Player(GamePanel gamePanel, KeyHandler key, String playerName, String suitName)
	{
		playerSize = gamePanel.tileSize;
		// extracting playerHash for PlayerSchema
		int playerHash ;
		if(gamePanel.getGameClient() != null) {
			playerHash = gamePanel.getGameClient().getClientHashCode();
		} else {
			playerHash = ((System.currentTimeMillis() * 100) + "default").hashCode();
		}

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
				PlayerDirection.idle,
				suitName);

		this.gamePanel = gamePanel;
		this.key = key ;
		// Set Default values
		worldX = 256;
		worldY = gamePanel.screenHeight / 2;
		speed = 2;
		playerSchema.setDirection(PlayerDirection.idle);

		//get the suit
		playerSuit = SuitHandler.getSuit(suitName);

		//movement handler
		this.movementHandler = new MovementHandler(this.playerSchema, this.gamePanel);
	}

	/**
	 * updates player location
	 * @return true if player is not in the same position as it was
	 */
	public boolean update()
	{
		int ix = playerSchema.getPlayerX();
		int iy = playerSchema.getPlayerY();

		movementHandler.handleJump();
		//Normal Moving process
		if(key.upPressed || key.rightPressed || key.leftPressed)
		{
			if(key.attackPressed)
			{
				// TODO: Implement updating attacking logics
			}
			if(key.upPressed)
			{
				playerSchema.setDirection(PlayerDirection.jump);
				movementHandler.startJump();
			}
			else if(key.rightPressed)
			{
				playerSchema.setDirection(PlayerDirection.run);
				movementHandler.movePlayer(playerSchema.getPlayerX() + speed, playerSchema.getPlayerY());
			}
			else if(key.leftPressed)
			{
				playerSchema.setDirection(PlayerDirection.runback);
				movementHandler.movePlayer(playerSchema.getPlayerX() - speed, playerSchema.getPlayerY());
			}
		}
		else if(movementHandler.isJumping) {// if player is on jump !
			playerSchema.setDirection(PlayerDirection.jump);

		}
		else {
		playerSchema.setDirection(PlayerDirection.idle);

		}

		// check for move
		if(ix != playerSchema.getPlayerX()
		|| iy != playerSchema.getPlayerY()){
			return true ;
		} else {
			return false ;
		}
	}

	/**
	 * Simply draws the player Image on the JPanel!
	 * @param g2 Graphic Object to draw
	 */
	public void draw(Graphics2D g2)
	{
		//State Image of Player
		BufferedImage image = getPlayerImage(playerSchema.getDirection(), playerSuit);
        g2.drawImage(image, playerSchema.getPlayerX(), playerSchema.getPlayerY(), playerSize, playerSize , null) ;

		// Set up the font and color for the text
		g2.setFont(new Font("Arial", Font.BOLD, 12)); // Adjust font and size as needed
		g2.setColor(playerSchema.getPlayerColor());

		// The text you want to display
		String text = playerSchema.getPlayerName(); // Replace with actual player name or desired text

		// Calculate the position for the text
		FontMetrics fm = g2.getFontMetrics();
		int textWidth = fm.stringWidth(text);
		int textX = playerSchema.getPlayerX() + (gamePanel.tileSize / 2) - (textWidth / 2); // Center the text above the player
		int textY = playerSchema.getPlayerY() - 2; // 2 pixels above the player, adjust as needed

		// Draw the text
		g2.drawString(text, textX, textY);
	}

	public static Color getRandomColor() {
		Random random = new Random();
		int r = random.nextInt(256); // Random value between 0 and 255
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		return new Color(r, g, b);
	}

	/**
	 * returns a PlayerMessage which is the stat of the player!
	 * about where it is and how he looks there!
	 * @return PlayerMessage
	 */
	public PlayerMessage getPlayerMessage() {
		return PlayerMessage.newBuilder()
				.setNameColor(nameColor)
				.setType(PlayerMessageType.PLAYER_MESSAGE_TYPE_MOVE)
				.setPlayerHash(playerSchema.getClientHashCode())
				.setSuitCode(playerSchema.getSuitName())
				.setDirection(playerSchema.getDirection().toString())
				.setName(playerSchema.getPlayerName())
				.setTimestamp(System.currentTimeMillis())
				.setX(playerSchema.getPlayerX())
				.setY(playerSchema.getPlayerY())
				.build();
	}

	/**
	 * returns the player image Based on direction and suit!
	 * @param direction direction of the player
	 * @param suit player suit object which contains player images
	 * @return image of the frame for the player
	 */
	public static BufferedImage getPlayerImage(PlayerDirection direction, PlayerSuit suit) {
        return switch (direction) {
			case jump -> suit.getJumpFrame();
			case idle -> suit.getIdleFrame();
			case run -> suit.getRunFrame();
			case runback -> suit.getRunBackFrame();
			default -> null;
		};
	}
}
