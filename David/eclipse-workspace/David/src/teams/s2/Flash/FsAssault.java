package teams.s2.Flash;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Game;
import core.Utility;
import objects.units.Assault;
import objects.units.BaseShip;
import objects.units.Miner;
import objects.units.Missile;
import objects.units.Raider;
import objects.units.Specialist;
import objects.units.Support;
import objects.units.Unit;

public class FsAssault extends Assault {
	Flash p;

	/***************** Constructor ***************/

	public FsAssault(Flash p) throws SlickException {
		super(p);
		this.p = p;
	}

	/***************** Action Method ***************/

	public void action() {
		Unit e = nearestEnemy();
		Unit mvm = nearestAlly(Miner.class);
		int index = 0;
		ArrayList<Unit> miners = getOwner().getMyUnits(Miner.class);

		for (int i = 0; i < miners.size(); i++) {
			if (miners.get(i).getDistance(e) < miners.get(index).getDistance(e)) {
				mvm = miners.get(i);
				index = i;
			}
		}
		if (getCurHealth() / getMaxHealth() < .25) {
			ability();
		}
		if (Game.getTime() / 80 < 80) {
			moveTo(mvm);
			shoot(e);
		} else {
			if (getOwner().countMyAssaults() < 2) {
				if (nearestAlly(Specialist.class) != null)
					circle(nearestAlly(Specialist.class));
				else if (mvm != null)
					circle(mvm);
				else if (nearestAlly(Raider.class) != null)
					circle(nearestAlly(Raider.class));
				else
					moveTo(getHomeBase());
				shoot(e);
			} else {
				if (e instanceof BaseShip && getHomeBase().getDistance(getEnemyBase()) < 1000) {
					moveTo(e);
					shoot(e);
				}
				if (e instanceof BaseShip) {
					circle(mvm);
					shoot(e);
				}
				if (e instanceof Assault) {
					circle(e);
					shoot(e);
				}
				if (e instanceof Raider) {
					circle(e);
					shoot(e);
				}
				if (e instanceof Specialist) {

					if (hasDamageReduction()) {
						circle(e);
						shoot(e);
					} else {
						if (getDistance(e) < 1055) {
							ability();
						}
						if (nearestAlly(Specialist.class) != null)
							circle(nearestAlly(Specialist.class));
						else if (mvm != null)
							circle(mvm);
						else if (nearestAlly(Raider.class) != null)
							circle(nearestAlly(Raider.class));
						else
							moveTo(getHomeBase());
						shoot(e);

					}
				}
				if (e instanceof Miner) {
					circle(e);
					shoot(e);
				}

				if (e instanceof Support) {
					if (nearestAlly(Specialist.class) != null)
						circle(nearestAlly(Specialist.class));
					else if (mvm != null)
						circle(mvm);
					else if (nearestAlly(Raider.class) != null)
						circle(nearestAlly(Raider.class));
					else
						moveTo(getHomeBase());
					shoot(e);

				}
			}
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

	public void circle(Unit u) {

		int angle = 0;

		angle = (22 - (int) (getDistance(u) * getDistance(u)) / 3000);

		if (angle < 12) {
			angle = 12;
		}

		turnTo(u);
		move((int) getAngleToward(u.getCenterX(), u.getCenterY()) + angle);

	}
}
