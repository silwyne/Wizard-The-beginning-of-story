package nilian.Player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import nilian.main.GamePanel;
import nilian.main.KeyHandler;


/**
 * Player object which includes all stuff a Player object must Contain
 */
public class Player extends Entity {

	private final Entity entity = new Entity() ;
	private final PlayerImages playerImages = new PlayerImages();

    private boolean isJumping = false;
    private boolean back = false ;
    private int jumpCount = 0;
	private final int playerSpeed = 1;

	public int playerX;
	public final int playerY;
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
	}


	public void setDefaultValues()
	{
		worldx = 256  ; 
		worldy = p.screenHeight / 2 ;
		speed = 4 ;
		direction = PlayerDirection.normal ;
	}


	public void jump() {
		if (!isJumping) {
			isJumping = true;
			verticalVelocity = -Math.sqrt(2 * GRAVITY * JUMP_HEIGHT); // Initial jump velocity
			initialY = worldy; // Store the initial Y position
		}
	}

	private final int JUMP_HEIGHT = 100; // Maximum jump height in pixels
	private final double GRAVITY = 0.5; // Gravity strength
	private double verticalVelocity = 0; // Current vertical velocity
	private int initialY; // Store the initial Y position when jump starts
	
	
	public void update()
	{
		// Jump logic
		if (isJumping) {
			// Apply gravity
			verticalVelocity += GRAVITY;

			// Update position
			worldy += verticalVelocity;

			// Check if we've returned to the ground
			if (worldy >= initialY) {
				worldy = initialY; // Ensure we don't go below the ground
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
				worldx += playerSpeed;
			}
			else if(key.leftPressed)
			{
				direction = PlayerDirection.runback ;
				worldx -= playerSpeed;
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
	}
}
