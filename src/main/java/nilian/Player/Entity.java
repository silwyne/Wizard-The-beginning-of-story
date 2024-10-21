package nilian.Player;

import java.awt.image.BufferedImage;

public class Entity {

	public int worldx , worldy ;
	public int speed ;
	public final int JUMP_HEIGHT = 80; // Maximum jump height in pixels
	public BufferedImage idle , jump , run ,runback ;
	public PlayerDirection direction ;
	public int spriteCounter = 0 ;

}
