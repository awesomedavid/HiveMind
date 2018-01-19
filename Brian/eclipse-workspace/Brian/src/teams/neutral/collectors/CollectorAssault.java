package teams.neutral.collectors;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import objects.base.Player;
import objects.units.Assault;
import objects.units.Unit;

public class CollectorAssault extends Assault {
	Collector p;


	public CollectorAssault(Player p) throws SlickException {
		super(p);
		this.p = (Collector) p;
	}
	

	public CollectorAssault(Player p, int x, int y) throws SlickException {
		super(p);
		this.p = (Collector) p;
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

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
