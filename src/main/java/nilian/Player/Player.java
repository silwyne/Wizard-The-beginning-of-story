package nilian.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import nilian.game.panel.GamePanel;
import nilian.input.KeyHandler;


/**
 * Player object which includes all stuff a Player object must Contain
 */
public class Player extends PlayerEntity {

	private final PlayerSchema playerSchema;
	private final Color playerNameColor;
	private final MovementHandler movementHandler;

	private final PlayerImages playerImages = new PlayerImages();
	private final Random random = new Random();

	GamePanel gamePanel;
	KeyHandler key;


	public Player(GamePanel gamePanel, KeyHandler key, String playerName)
	{
		// extracting playerHash for PlayerSchema
		int playerHash ;
		if(gamePanel.getGameClient() != null) {
			playerHash = gamePanel.getGameClient().getClientHashCode();
		} else {
			playerHash = ((System.currentTimeMillis() * 100) + "default").hashCode();
		}
		this.playerSchema = new PlayerSchema(playerName, playerHash, gamePanel.screenHeight / 2, gamePanel.screenHeight / 2);
		this.gamePanel = gamePanel;
		this.key = key ;
		// Set Default values
		worldX = 256;
		worldY = gamePanel.screenHeight / 2;
		speed = 2;
		direction = PlayerDirection.normal;

        try {
			//load player images
            playerImages.getImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
		//cutting images into image arrays
        playerImages.separate();

		playerNameColor = getRandomColor();
		//movement handler
		this.movementHandler = new MovementHandler(this.playerSchema, this.gamePanel);
	}

	public void update()
	{
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
		sprite();
	}
	

	public int spriteIdle = 0 ;
	public int spriteJump = 0 ;
	public int spriteRun = 0 ;
	public int totalIdle = 4 ;
	public int totalJump = 4 ;
	public int totalRun = 6 ;

	/**
	 * Updates Player Images frame by frame!
	 */
	public void sprite()
	{
		spriteCounter ++ ;
		if(spriteCounter > 6)
		{
			//sprite for idle images
			if(spriteIdle == totalIdle-1)
			{
				spriteIdle = 0 ;
				spriteCounter = 0 ;
			}
		    else if(spriteIdle < totalIdle)
			{
				spriteIdle ++ ;
			}
			//sprite for jump images
			if(spriteJump == totalJump-1)
			{
				spriteJump = 0 ;
				spriteCounter = 0 ;
			}
		    else if(spriteJump < totalJump)
			{
		    	spriteJump ++ ;
			}	
			//sprite for run images
			if(spriteRun == totalRun-1)
			{
				spriteRun = 0 ;
				spriteCounter = 0 ;
			}
		    else if(spriteRun < totalRun)
			{
		    	spriteRun ++ ;
			}
		}
	}


	/**
	 * Simply draws the player Image on the JPanel!
	 * @param g2 Graphic Object to draw
	 */
	public void draw(Graphics2D g2)
	{
		//State Image of Player
		BufferedImage image = null ;
		switch(direction) 
		{
		
		case jump :
			image = playerImages.jumpParts[spriteJump].image ;
			break ;
			
			
		case idle :
			image = playerImages.idleParts[spriteIdle].image ;
			break ;
			
			
		case run :
			image = playerImages.runParts[spriteRun].image ;
			break ;
			
		case runback :
			image = playerImages.runBackParts[spriteRun].image ;
			break ;
		}
		g2.drawImage(image, playerSchema.getPlayerX(), playerSchema.getPlayerY(), gamePanel.tileSize, gamePanel.tileSize , null) ;

		// Set up the font and color for the text
		g2.setFont(new Font("Arial", Font.BOLD, 12)); // Adjust font and size as needed
		g2.setColor(playerNameColor);

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

	private Color getRandomColor() {
		return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}
}
