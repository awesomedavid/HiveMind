package teams.neutral.collectors;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Utility;
import core.Values;
import objects.base.Player;
import objects.units.Assault;
import objects.units.BaseShip;
import objects.units.Miner;
import objects.units.Raider;
import objects.units.Unit;

public class CollectorRaider extends Raider {
	Collector p;
	int squad = 0;
	public CollectorRaider(Player p) throws SlickException {
		super(p);
		this.p = (Collector) p;
	}
	

	public CollectorRaider(Player p, int x, int y) throws SlickException {
		super(p);
		this.p = (Collector) p;
		this.x = x;
		this.y = y;
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


	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
}
