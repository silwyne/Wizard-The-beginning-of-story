package nilian.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import nilian.main.GamePanel;
import nilian.main.KeyHandler;


/**
 * Player object which includes all stuff a Player object must Contain
 */
public class Player extends Entity {

	private final String playerName = "Seyed!" ;
	private final Color playerNameColor ;


	private final Entity entity = new Entity() ;
	private final PlayerImages playerImages = new PlayerImages();
	private final Random random = new Random();
    private boolean isJumping = false;

	public int playerX;
	public int playerY;
    GamePanel p;
	KeyHandler key;


	public Player(GamePanel p, KeyHandler key)
	{
		this.p = p ;
		this.key = key ;
		playerX = p.screenHeight / 2 ;
		playerY = p.screenHeight/2 ;
		setDefaultValues() ;

        try {
			//load player images
            playerImages.getImages() ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
		//cutting images into image arrays
        playerImages.separate() ;

		playerNameColor = getRandomColor();
	}


	public void setDefaultValues()
	{
		worldx = 256  ; 
		worldy = p.screenHeight / 2 ;
		speed = 2 ;
		direction = PlayerDirection.normal ;
	}


	public void jump() {
		if (!isJumping) {
			isJumping = true;
			verticalVelocity = -Math.sqrt(2 * GRAVITY * JUMP_HEIGHT); // Initial jump velocity
			initialY = worldy; // Store the initial Y position
		}
	}

	private final double GRAVITY = 0.25; // Gravity strength
	private double verticalVelocity = 0; // Current vertical velocity
	private int initialY; // Store the initial Y position when jump starts

	
	public void update()
	{
		if (isJumping) {
			// Apply gravity
			verticalVelocity += GRAVITY;

			// Update player's position
			playerY += verticalVelocity;

			// Check if we've returned to the ground
			if (playerY >= initialY) {
				playerY = initialY; // Ensure player doesn't go below the ground
				isJumping = false;
				verticalVelocity = 0;
			}
		}
		//Normal Moving process
		if(key.upPressed || key.rightPressed || key.leftPressed)
		{
			if(key.upPressed)
			{
				direction = PlayerDirection.jump ;
				jump() ;
			}
			else if(key.rightPressed)
			{
				direction = PlayerDirection.run ;
				playerX += speed;
			}
			else if(key.leftPressed)
			{
				direction = PlayerDirection.runback ;
				playerX -= speed;
			}
		} else {
			direction = PlayerDirection.idle ;
		}
		sprite() ;
	}
	
	
	public int spriteIdle = 0 ;
	public int spriteJump = 0 ;
	public int spriteRun = 0 ;
	public int totalIdle = 4 ;
	public int totalJump = 4 ;
	public int totalRun = 6 ;

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


	public void draw(Graphics2D g2)
	{
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
		g2.drawImage(image, playerX, playerY, p.tileSize, p.tileSize , null) ;

		// Set up the font and color for the text
		g2.setFont(new Font("Arial", Font.BOLD, 12)); // Adjust font and size as needed
		g2.setColor(playerNameColor);

		// The text you want to display
		String text = playerName; // Replace with actual player name or desired text

		// Calculate the position for the text
		FontMetrics fm = g2.getFontMetrics();
		int textWidth = fm.stringWidth(text);
		int textX = playerX + (p.tileSize / 2) - (textWidth / 2); // Center the text above the player
		int textY = playerY - 10; // 10 pixels above the player, adjust as needed

		// Draw the text
		g2.drawString(text, textX, textY);
	}

	private Color getRandomColor() {
		return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}
}
