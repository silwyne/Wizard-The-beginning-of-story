package nilian.tile;

import java.awt.image.BufferedImage;

public class Tile 
{
	public BufferedImage image ;
	public boolean collision = false ;

	public Tile() {}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}
}