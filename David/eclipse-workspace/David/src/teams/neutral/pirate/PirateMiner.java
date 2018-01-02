package teams.neutral.pirate;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import objects.base.Player;
import objects.units.Miner;

public class PirateMiner extends Miner {
	Pirate p;


	public PirateMiner(Player p) throws SlickException {
		super(p);
		this.p = (Pirate) p;
	}
	

	public PirateMiner(Player p, int x, int y) throws SlickException {
		super(p);
		this.p = (Pirate) p;
		this.x = x;
		this.y = y;
	}

	public void action() {
		if (isFull()) {
			moveTo(getHomeBase());
		} else {
			moveTo(nearestOpenAsteroid());
			startMine(nearestOpenAsteroid());
		}
	}

	protected void attack() 	 {	}
	protected void defend()		 {	}
	protected void guard() 		 {	}
	protected void rally()		 {	}
	protected void skirmish() 	 {	}
	protected void special() 	 {	}
	protected void run() 		 {  }

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
