package nilian.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import nilian.main.GamePanel;
import nilian.main.KeyHandler;


/**
 * Player object which includes all stuff a Player object must Contain
 */
public class Player extends Entity{
	
	Image[] idleParts;
	Image[] jumpParts;
	Image[] runParts;
	Image[] runBackParts;
	
    private final int jumpHeight = 20;
    private boolean isJumping = false;
    private boolean back = false ;
    private int jumpCount = 0;
	
	public final int screenx ;
	public final int screeny ;
    GamePanel p;
	KeyHandler key;


	public Player(GamePanel p, KeyHandler key)
	{
		this.p = p ;
		this.key = key ;
		screenx = p.screenHeight / 2 ;
		screeny = p.screenHeight/2 ;
		setDefaultValues() ;
		getPlayerImage() ;
	}


	public void setDefaultValues()
	{
		worldx = 256  ; 
		worldy = p.screenHeight / 2 ;
		speed = 4 ;
		direction = "normal" ;
	}


	public void getPlayerImage()
	{
		try
		{
			idle = ImageIO.read(getClass().getResourceAsStream("/Player/Cyborg_idle.png")) ;
			jump = ImageIO.read(getClass().getResourceAsStream("/Player/Cyborg_jump.png")) ;
			run = ImageIO.read(getClass().getResourceAsStream("/Player/Cyborg_run.png")) ;
			runback = ImageIO.read(getClass().getResourceAsStream("/Player/Cyborg_run-backward.png")) ;
			separate() ;

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}


	public void separate()
	{
		int width = idle.getWidth();
		int height = idle.getHeight();
		int partWidth = 48;
		int num = width / partWidth;
		int remainingWidth = width % partWidth;
		idleParts = new Image[num];
		for (int i = 0; i < num; i++) {
		    int x = i * partWidth;
		    int y = 0;
		    int partHeight = height;
		    if (i == num - 1 && remainingWidth > 0) {
		        partWidth = remainingWidth;
		    }
		    BufferedImage part = idle.getSubimage(x, y, partWidth, partHeight);

		    idleParts[i] = new Image();
		    idleParts[i].image = part;
		}
		
		width = jump.getWidth();
		height = jump.getHeight();
		partWidth = 48;
		num = width / partWidth;
		remainingWidth = width % partWidth;
		jumpParts = new Image[num];
		for (int i = 0; i < num; i++) {
		    int x = i * partWidth;
		    int y = 0;
		    int partHeight = height;
		    if (i == num - 1 && remainingWidth > 0) {
		        partWidth = remainingWidth;
		    }
		    BufferedImage part = jump.getSubimage(x, y, partWidth, partHeight);

		    jumpParts[i] = new Image();
		    jumpParts[i].image = part;
		}
		
		width = run.getWidth();
		height = run.getHeight();
		partWidth = 48;
		num = width / partWidth;
		remainingWidth = width % partWidth;
		runParts = new Image[num];
		for (int i = 0; i < num; i++) {
		    int x = i * partWidth;
		    int y = 0;
		    int partHeight = height;
		    if (i == num - 1 && remainingWidth > 0) {
		        partWidth = remainingWidth;
		    }
		    BufferedImage part = run.getSubimage(x, y, partWidth, partHeight);

		    runParts[i] = new Image();
		    runParts[i].image = part;
		}
		
		width = runback.getWidth();
		height = runback.getHeight();
		partWidth = 48;
		num = width / partWidth;
		remainingWidth = width % partWidth;
		runBackParts = new Image[num];
		for (int i = 0; i < num; i++) {
		    int x = i * partWidth;
		    int y = 0;
		    int partHeight = height;
		    if (i == num - 1 && remainingWidth > 0) {
		        partWidth = remainingWidth;
		    }
		    BufferedImage part = runback.getSubimage(x, y, partWidth, partHeight);

		    runBackParts[i] = new Image();
		    runBackParts[i].image = part;
		}
	}


	public void jump()
	{
		if (!isJumping) {
            isJumping = true;
            jumpCount = 0;
        }
	}
	
	
	
	public void update()
	{
		if(back)
    	{
    		if (jumpCount >= 0) {
                worldy += 5;
                jumpCount--;
            }
            else {
                back = false;
                jumpCount = 0;
            }
    	}
        if (isJumping) {
            if (jumpCount <= jumpHeight) {
                worldy -= 5;
                jumpCount++;
                if(jumpCount == jumpHeight)
                {
                	back = true ; 
                	isJumping = false;
                }
            }
                
        } else {
            worldy = 200;
        }
		if(key.upPressed || key.rightPressed || key.leftPressed)
		{
			if(key.upPressed || isJumping)
			{
				direction = "jump" ;
				jump() ;
			}
			else if(key.rightPressed)
			{
				direction = "run" ;
			}
			else if(key.leftPressed)
			{
				direction = "runback" ;
			}
		}
		else
		{
			direction = "idle" ;
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
		//		g2.setColor(Color.WHITE);
		//		g2.fillRect(x , y , gp.tileSize, gp.tileSize);

		BufferedImage image = null ;

		switch(direction) 
		{
		
		case "jump" :
			image = jumpParts[spriteJump].image ;
			break ;
			
			
		case "idle" :
			image = idleParts[spriteIdle].image ;
			break ;
			
			
		case "run" :
			image = runParts[spriteRun].image ;
			break ;
			
		case "runback" :
			image = runBackParts[spriteRun].image ;
			break ;
		}
		g2.drawImage(image, screenx, screeny, p.tileSize, p.tileSize , null) ;
	}
}
