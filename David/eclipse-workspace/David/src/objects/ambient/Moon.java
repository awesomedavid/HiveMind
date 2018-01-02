package objects.ambient;

import org.newdawn.slick.SlickException;

import core.Game;
import core.Values;
import ui.display.Images;

public class Moon extends Asteroid 
{
	public Moon(float x, float y, int size) throws SlickException {
		
		super(x, y, 0, 0, size);
		image = Images.moon;
		turnSpeed = 0;
				
		scale = .6f;
		minerals = Integer.MAX_VALUE;
		
		x = -image.getWidth() * scale ;
		y = -image.getHeight() * scale;
	}

	public void update() {

		
		
	}
	
	
	
	
}
