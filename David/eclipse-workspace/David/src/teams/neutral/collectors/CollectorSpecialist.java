package teams.neutral.collectors;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import objects.base.Player;
import objects.units.Miner;
import objects.units.Specialist;
import objects.units.Unit;

public class CollectorSpecialist extends Specialist {
	Collector p;


	public CollectorSpecialist(Player p) throws SlickException {
		super(p);
		this.p = (Collector) p;
	}
	

	public CollectorSpecialist(Player p, int x, int y) throws SlickException {
		super(p);
		this.p = (Collector) p;
		this.x = x;
		this.y = y;
	}

	public void action() {

		Unit a = nearestAlly(Miner.class);
		moveTo(a);
		
		Unit t = nearestEnemy();
		shoot(t);

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
