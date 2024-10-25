package nilian.tile;

import java.awt.image.BufferedImage;

public class Tile 
{
	public BufferedImage image ;
	public boolean moveable = true ;

	public Tile() {}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}
}