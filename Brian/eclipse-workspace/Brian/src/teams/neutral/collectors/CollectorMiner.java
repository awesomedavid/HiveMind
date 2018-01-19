package teams.neutral.collectors;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import objects.base.Player;
import objects.units.Miner;
import objects.units.Unit;

public class CollectorMiner extends Miner {
	Collector p;


	public CollectorMiner(Player p) throws SlickException {
		super(p);
		this.p = (Collector) p;
	}
	

	public CollectorMiner(Player p, int x, int y) throws SlickException {
		super(p);
		this.p = (Collector) p;
		this.x = x;
		this.y = y;
	}

	public void action() 
	{
		Unit a = nearestEnemy();
		shoot(a);
		
		if(hasOpenAsteroid())
		{
			if (isFull()) {
				moveTo(getHomeBase());
			} else {
				moveTo(nearestOpenAsteroid());
				startMine(nearestOpenAsteroid());
			}
		}
		else
		{
			moveTo(a);
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
