package nilian.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import nilian.main.GamePanel;
import nilian.main.KeyHandler;

public class Player extends Entity{
	
	Image[] idleparts;
	Image[] jumpparts;
	Image[] runparts;
	Image[] runbackparts ;
	
    private int jumpHeight = 20;
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
		direction = PlayerDirection.normal ;
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
		idleparts = new Image[num];
		for (int i = 0; i < num; i++) {
		    int x = i * partWidth;
		    int y = 0;
		    int partHeight = height;
		    if (i == num - 1 && remainingWidth > 0) {
		        partWidth = remainingWidth;
		    }
		    BufferedImage part = idle.getSubimage(x, y, partWidth, partHeight);

		    idleparts[i] = new Image();
		    idleparts[i].image = part;
		}
		
		width = jump.getWidth();
		height = jump.getHeight();
		partWidth = 48;
		num = width / partWidth;
		remainingWidth = width % partWidth;
		jumpparts = new Image[num];
		for (int i = 0; i < num; i++) {
		    int x = i * partWidth;
		    int y = 0;
		    int partHeight = height;
		    if (i == num - 1 && remainingWidth > 0) {
		        partWidth = remainingWidth;
		    }
		    BufferedImage part = jump.getSubimage(x, y, partWidth, partHeight);

		    jumpparts[i] = new Image();
		    jumpparts[i].image = part;
		}
		
		width = run.getWidth();
		height = run.getHeight();
		partWidth = 48;
		num = width / partWidth;
		remainingWidth = width % partWidth;
		runparts = new Image[num];
		for (int i = 0; i < num; i++) {
		    int x = i * partWidth;
		    int y = 0;
		    int partHeight = height;
		    if (i == num - 1 && remainingWidth > 0) {
		        partWidth = remainingWidth;
		    }
		    BufferedImage part = run.getSubimage(x, y, partWidth, partHeight);

		    runparts[i] = new Image();
		    runparts[i].image = part;
		}
		
		width = runback.getWidth();
		height = runback.getHeight();
		partWidth = 48;
		num = width / partWidth;
		remainingWidth = width % partWidth;
		runbackparts = new Image[num];
		for (int i = 0; i < num; i++) {
		    int x = i * partWidth;
		    int y = 0;
		    int partHeight = height;
		    if (i == num - 1 && remainingWidth > 0) {
		        partWidth = remainingWidth;
		    }
		    BufferedImage part = runback.getSubimage(x, y, partWidth, partHeight);

		    runbackparts[i] = new Image();
		    runbackparts[i].image = part;
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
				direction = PlayerDirection.jump ;
				jump() ;
			}
			else if(key.rightPressed)
			{
				direction = PlayerDirection.run ;
			}
			else if(key.leftPressed)
			{
				direction = PlayerDirection.runback ;
			}
		} else {
			direction = PlayerDirection.idle ;
		}
		sprite() ;
	}
	
	
	
	
	
	
	
	public int spriteidle = 0 ;
	public int spritejump = 0 ;
	public int spriterun = 0 ;
	public int totalidle = 4 ;
	public int totaljump = 4 ;
	public int totalrun = 6 ;
	public void sprite()
	{
		spriteCounter ++ ;
		if(spriteCounter > 6)
		{
			//sprite for idle images
			if(spriteidle == totalidle-1)
			{
				spriteidle = 0 ;
				spriteCounter = 0 ;
			}
		    else if(spriteidle < totalidle)
			{
				spriteidle ++ ;
			}
			//sprite for jump images
			if(spritejump == totaljump-1)
			{
				spritejump = 0 ;
				spriteCounter = 0 ;
			}
		    else if(spritejump < totaljump)
			{
		    	spritejump ++ ;
			}	
			//sprite for run images
			if(spriterun == totalrun-1)
			{
				spriterun = 0 ;
				spriteCounter = 0 ;
			}
		    else if(spriterun < totalrun)
			{
		    	spriterun ++ ;
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
		
		case jump :
			image = jumpparts[spritejump].image ;
			break ;
			
			
		case idle :
			image = idleparts[spriteidle].image ;
			break ;
			
			
		case run :
			image = runparts[spriterun].image ;
			break ;
			
		case runback :
			image = runbackparts[spriterun].image ;
			break ;
		}
		g2.drawImage(image, screenx, screeny, p.tileSize, p.tileSize , null) ;
	}
}
