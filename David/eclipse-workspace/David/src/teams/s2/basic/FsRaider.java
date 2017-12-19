package teams.s2.basic;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import objects.units.Assault;
import objects.units.BaseShip;
import objects.units.Miner;
import objects.units.Raider;
import objects.units.Specialist;
import objects.units.Support;
import objects.units.Unit;

public class FsRaider extends Raider {
	Fs p;

	public FsRaider(Fs p) throws SlickException {
		super(p);
		this.p = p;
	}

	/***************** Action Method ***************/
	
	public void action() {
		
		Unit e = nearestEnemy();
		Unit a = nearestAlly();
		if(e instanceof BaseShip) {
			moveTo(nearestAlly(Miner.class));
			shoot(e);
		}
		if(e instanceof Assault) {
			
			moveTo(nearestAlly(Assault.class));
			moveTo(nearestAlly(Specialist.class));
			moveTo(getHomeBase());
			shoot(e);
		}
		if(e instanceof Raider) {
			moveTo(e);
			shoot(e);
		}
		if(e instanceof Specialist) {
			moveTo(e);
			shoot(e);
		}
		if(e instanceof Miner){
			moveTo(e);
			shoot(e);
		}
		if(e instanceof Support) {
			moveTo(e);
			shoot(e);
		}
		if(getOwner().countEnemyUnits()<=0 || e == null) {
			moveTo(getEnemyBase());
			shoot(getEnemyBase());
		}
		
		

	}

	/***************** Order Methods ***************/

	protected void attack() {
		// This method is called every frame while the unit's order is set to ATTACK
	}

	protected void defend() {
		// This method is called every frame while the unit's order is set to DEFEND
	}

	protected void guard() {
		// This method is called every frame while the unit's order is set to GUARD
	}

	protected void rally() {
		// This method is called every frame while the unit's order is set to RALLY
	}

	protected void skirmish() {
		// This method is called every frame while the unit's order is set to SKIRMISH
	}

	protected void special() {
		// This method is called every frame while the unit's order is set to SPECIAL
	}

	protected void run() {
		// This method is called every frame while the unit's order is set to RUN
	}

	/***************** DRAW Methods ***************/
	
	public void draw(Graphics g) {

		// This method allows you to draw things on the screen.  It's only visible if you enable  
		// that player's drawings.  Press 'q' to enable drawings for BLUE and 'e' for RED.
	}
}
