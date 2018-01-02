package teams.s2.Fs;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Utility;
import objects.units.Assault;
import objects.units.BaseShip;
import objects.units.Miner;
import objects.units.Raider;
import objects.units.Specialist;
import objects.units.Support;
import objects.units.Unit;

public class FsSpecialist extends Specialist {
	Fs p;

	public FsSpecialist(Fs p) throws SlickException {
		super(p);
		this.p = p;
	}

	/***************** Action Method ***************/
	
	public void action() {
		Unit e = nearestEnemy();
		if(e instanceof BaseShip && Utility.distance(getHomeBase(),getEnemyBase())<500) {
			moveTo(e);
			shoot(e);
		}
		if(e instanceof BaseShip) {
			moveTo(nearestAlly(Miner.class));
			shoot(e);
		}
		if(e instanceof Assault) {
			if(getDistance(e)<1000) {
				turnTo(e);
				move(180);
			}
			moveTo(e);			
			shoot(e);
		}
		if(e instanceof Raider) {
			if(nearestAlly(Assault.class)!=null)
			moveTo(nearestAlly(Assault.class));
			else if(nearestAlly(Raider.class)!=null)
			moveTo(nearestAlly(Raider.class));
			else
			moveTo(getHomeBase());
			shoot(e);
		}
		if(e instanceof Specialist) {
			if(getDistance(e)<1000) {
				turnTo(e);
				move(180);
			}
			moveTo(e);			
			shoot(e);
		}
		if(e instanceof Miner){
			if(getDistance(e)<1000) {
				turnTo(e);
				move(180);
			}
			moveTo(e);			
			shoot(e);
		}
		if(e instanceof Support) {
			if(getDistance(e)<1000) {
				turnTo(e);
				move(180);
			}
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
