package teams.s2.Fs.Raiders;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Game;
import core.Utility;
import objects.units.Assault;
import objects.units.BaseShip;
import objects.units.Miner;
import objects.units.Raider;
import objects.units.Specialist;
import objects.units.Support;
import objects.units.Unit;
import objects.units.Unit.Order;
import teams.s2.Fs.Fs;

public class FsRaider extends Raider {
	Fs p;

	public FsRaider(Fs p) throws SlickException {
		super(p);
		this.p = p;
	}

	/***************** Action Method ***************/

	public void action() {

		Unit e = nearestEnemy();
		// if (e instanceof BaseShip && Utility.distance(getHomeBase(), getEnemyBase())
		// < 500) {
		// moveTo(e);
		// shoot(e);
		// }
		// if (e instanceof BaseShip) {
		// moveTo(nearestAlly(Miner.class));
		// shoot(e);
		// }
		//
		// if (e instanceof Assault) {
		// if (nearestAlly(Assault.class) != null)
		// moveTo(nearestAlly(Assault.class));
		// else if (nearestAlly(Specialist.class) != null)
		// moveTo(nearestAlly(Specialist.class));
		// else
		// moveTo(getHomeBase());
		// shoot(e);
		// }
		// if (Utility.distance(e, nearestEnemy(Assault.class)) < 390) {
		// if (nearestAlly(Specialist.class) != null) {
		// moveTo(nearestAlly(Specialist.class));
		// shoot(e);
		// } else {
		// turnTo(nearestEnemy(Assault.class));
		// moveTo(getHomeBase());
		// shoot(e);
		// }
		// } else {
		//
		// if (e instanceof Raider) {
		// moveTo(e);
		// shoot(e);
		// }
		// if (e instanceof Specialist) {
		// moveTo(e);
		// shoot(e);
		// }
		// if (e instanceof Miner) {
		// moveTo(e);
		// shoot(e);
		// }
		// if (e instanceof Support) {
		// moveTo(e);
		// shoot(e);
		// }
		// if (getOwner().countEnemyUnits() <= 0 || e == null) {
		// moveTo(getEnemyBase());
		// shoot(getEnemyBase());
		// }
		// }

		setOrder(Order.DEFEND);

		shoot(e);

	}

	/***************** Order Methods ***************/

	protected void attack() {
		// This method is called every frame while the unit's order is set to ATTACK
		Unit e = nearestEnemy();

		if (p.countEnemyUnits() - p.countEnemyMiners() > 5) {
			if (getDistance(e) < 2000 && p.countEnemyMiners() > 0) {
				if (e instanceof Miner) {
					moveTo(e);
				} else {
					turnTo(e);
					move((int) getAngleToward(e.getCenterX(), e.getCenterY()) + 120);
				}
			} else {
				if (p.getMostVulerableEnemy(Miner.class) != null) {
					moveTo(p.getMostVulerableEnemy(Miner.class));
				} else {
					moveTo(e);
				}
			}
		} else {
			if (Game.getTime() > 10000 && getDistance(getEnemyBase()) < 7500 && p.countMyRaiders() > 15) {
				moveTo(e);
			} else {
				moveTo(getHomeBase());
			}
		}
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

		// This method allows you to draw things on the screen. It's only visible if you
		// enable
		// that player's drawings. Press 'q' to enable drawings for BLUE and 'e' for
		// RED.
	}
}
