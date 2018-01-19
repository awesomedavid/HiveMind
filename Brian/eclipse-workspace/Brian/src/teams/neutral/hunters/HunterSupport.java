package teams.neutral.hunters;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import objects.base.Player;
import objects.units.Support;
import objects.units.Unit;

public class HunterSupport extends Support {
	Hunter p;


	public HunterSupport(Player p) throws SlickException {
		super(p);
		this.p = (Hunter) p;
	}
	

	public HunterSupport(Player p, int x, int y) throws SlickException {
		super(p);
		this.p = (Hunter) p;
		this.x = x;
		this.y = y;
	}

	public void action() {
		Unit u = nearestAlly();
		moveTo(nearestAllyExclude(Support.class));

		if (u != null && u.isDamaged()) {
			shoot(u);
		}

	}

	protected void attack() 	 {	}
	protected void defend()		 {	}
	protected void guard() 		 {	}
	protected void rally()		 {	}
	protected void skirmish() 	 {	}
	protected void special() 	 {	}
	protected void run() 		 {  }
	
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
