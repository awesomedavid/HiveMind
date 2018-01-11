package teams.neutral.hunters;

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

public class HunterRaider extends Raider {
	Hunter p;
	int squad = 0;
	public HunterRaider(Player p) throws SlickException {
		super(p);
		this.p = (Hunter) p;
	}
	

	public HunterRaider(Player p, int x, int y) throws SlickException {
		super(p);
		this.p = (Hunter) p;
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
