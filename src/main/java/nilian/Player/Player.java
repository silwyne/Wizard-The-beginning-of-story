package nilian.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import nilian.Player.suit.PlayerSuit;
import nilian.game.panel.GamePanel;
import nilian.input.KeyHandler;
import nilian.online.message.PlayerMessage;


/**
 * Player object which includes all stuff a Player object must Contain
 */
public class Player extends PlayerEntity {

	public final PlayerSchema playerSchema;
	private final MovementHandler movementHandler;

	private final PlayerSuit playerSuit;
	private static final Random random = new Random();

	GamePanel gamePanel;
	KeyHandler key;


	public Player(GamePanel gamePanel, KeyHandler key, String playerName)
	{
		playerSize = gamePanel.tileSize;
		// extracting playerHash for PlayerSchema
		int playerHash ;
		if(gamePanel.getGameClient() != null) {
			playerHash = gamePanel.getGameClient().getClientHashCode();
		} else {
			playerHash = ((System.currentTimeMillis() * 100) + "default").hashCode();
		}
		this.playerSchema = new PlayerSchema(
				playerName,
				playerHash,
				gamePanel.screenHeight / 2,
				gamePanel.screenHeight / 2,
				getRandomColor(),
				playerSize);
		this.gamePanel = gamePanel;
		this.key = key ;
		// Set Default values
		worldX = 256;
		worldY = gamePanel.screenHeight / 2;
		speed = 2;
		direction = PlayerDirection.normal;

		// loading the player suit
		playerSuit = new PlayerSuit("/Player/suit_1");

		// set images number
		playerSuit.setImagesNum(4, 4, 6, 6);
		// load player images
		playerSuit.getImages();
		// cutting images into image arrays
		playerSuit.separate();

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
			if(key.upPressed)
			{
				direction = PlayerDirection.jump;
				movementHandler.startJump();
			}
			else if(key.rightPressed)
			{
				direction = PlayerDirection.run;
				movementHandler.movePlayer(playerSchema.getPlayerX() + speed, playerSchema.getPlayerY());
			}
			else if(key.leftPressed)
			{
				direction = PlayerDirection.runback;
				movementHandler.movePlayer(playerSchema.getPlayerX() - speed, playerSchema.getPlayerY());
			}
		}
		else if(movementHandler.isJumping) {// if player is on jump !
			direction = PlayerDirection.jump;

		}
		else {
		direction = PlayerDirection.idle;

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
		BufferedImage image = switch (direction) {
            case jump -> playerSuit.getJumpFrame();
            case idle -> playerSuit.getIdleFrame();
            case run -> playerSuit.getRunFrame();
            case runback -> playerSuit.getRunBackFrame();
            default -> null;
        };
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
		return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}

	public PlayerMessage getPlayerMessage() {
		return PlayerMessage.newBuilder()
				.setPlayerHash(playerSchema.getClientHashCode())
				.setSuitCode(1)
				.setName(playerSchema.getPlayerName())
				.setTimestamp(System.currentTimeMillis())
				.setX(playerSchema.getPlayerX())
				.setY(playerSchema.getPlayerY())
				.setNameColor(playerSchema.getPlayerColor().toString())
				.build();
	}
}
