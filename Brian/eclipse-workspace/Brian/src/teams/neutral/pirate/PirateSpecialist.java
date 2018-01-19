package teams.neutral.pirate;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import objects.base.Player;
import objects.units.Specialist;
import objects.units.Unit;

public class PirateSpecialist extends Specialist {
	Pirate p;


	public PirateSpecialist(Player p) throws SlickException {
		super(p);
		this.p = (Pirate) p;
	}
	

	public PirateSpecialist(Player p, int x, int y) throws SlickException {
		super(p);
		this.p = (Pirate) p;
		this.x = x;
		this.y = y;
	}

	public void action() {

		Unit a = nearestEnemy();

		moveTo(a);
		shoot(a);

	}

	protected void attack() 	 {	}
	protected void defend()		 {	}
	protected void guard() 		 {	}
	protected void rally()		 {	}
	protected void skirmish() 	 {	}
	protected void special() 	 {	}
	protected void run() 		 {  }
	

	public void draw(Graphics g) {
		
	}

}
