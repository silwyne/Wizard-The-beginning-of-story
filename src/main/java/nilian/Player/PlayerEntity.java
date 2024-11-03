package nilian.Player;

import java.awt.image.BufferedImage;

public class PlayerEntity {

	public int worldX, worldY;
	public int speed ;
	public final int JUMP_HEIGHT = 80; // Maximum jump height in pixels
	public BufferedImage idle , jump , run , runBack;
	public PlayerDirection direction ;
	public int spriteCounter = 0 ;

}
