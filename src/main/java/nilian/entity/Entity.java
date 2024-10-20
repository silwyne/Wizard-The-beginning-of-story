package nilian.entity;

import nilian.Player.PlayerDirection;

import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldx , worldy ;
	public int speed ;
	public BufferedImage idle , jump , run ,runback ;
	public PlayerDirection direction ;
	public int spriteCounter = 0 ;
	
}
