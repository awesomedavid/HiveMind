package teams.s2.Flash;

import java.util.ArrayList;

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

public class FsRaider extends Raider {
	Flash p;

	public FsRaider(Flash p) throws SlickException {
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
		Unit mvs = nearestAlly(Specialist.class);
		int index2 = 0;
		ArrayList<Unit> specials = getOwner().getMyUnits(Specialist.class);
		for (int i = 0; i < specials.size(); i++) {
			if (specials.get(i).getDistance(e) < specials.get(index2).getDistance(e)) {
				mvs = specials.get(i);
				index = i;
			}
		}
		if (Game.getTime()/80 < 80) {
			circle(mvm);
			shoot(e);
		}else if(getOwner().countMyRaiders()<8) {
			if (mvs != null) {
				if (mvm != null && mvm.getDistance(e) < mvs.getDistance(e)) {
					circle(mvm);
					shoot(e);
				} else {
					circle(mvs);
					shoot(e);
				}
			} else {
				circle(nearestAlly(Assault.class));
				shoot(e);
			}
		}
		else {
			if (e instanceof BaseShip && getHomeBase().getDistance(getEnemyBase()) < 1000) {
				moveTo(e);
				shoot(e);
			}
			if (e instanceof BaseShip) {
				circle(mvm);
				shoot(e);
			}

			if (e instanceof Assault) {
				if (mvs != null) {
					if (mvm != null && mvm.getDistance(e) < mvs.getDistance(e)) {
						circle(mvm);
						shoot(e);
					} else {
						circle(mvs);
						shoot(e);
					}
				} else {
					circle(nearestAlly(Assault.class));
					shoot(e);
				}
			}

			if (e instanceof Raider) {
				if (e.getDistance(getEnemyBase()) < 500) {
					if (mvs != null) {
						circle(mvs);
						shoot(e);
					} else if (nearestAlly(Assault.class) != null) {
						circle(nearestAlly(Assault.class));
						shoot(e);
					} else {
						circle(mvm);
						shoot(e);
					}
				} else {
					moveTo(e);
					shoot(e);
				}
			}
			if (e instanceof Specialist) {
				if (e.getDistance(getEnemyBase()) < 500) {
					if (mvs != null) {
						circle(mvs);
						shoot(e);
					} else if (nearestAlly(Assault.class) != null) {
						circle(nearestAlly(Assault.class));
						shoot(e);
					} else {
						circle(mvm);
						shoot(e);
					}
				} else {
					moveTo(e);
					shoot(e);
				}
			}
			if (e instanceof Miner) {
				if (e.getDistance(getEnemyBase()) < 500) {
					if (mvs != null) {
						circle(mvs);
						shoot(e);
					} else if (nearestAlly(Assault.class) != null) {
						circle(nearestAlly(Assault.class));
						shoot(e);
					} else {
						circle(mvm);
						shoot(e);
					}
				} else {
					moveTo(e);
					shoot(e);
				}
			}
			if (e instanceof Support) {
				if (e.getDistance(getEnemyBase()) < 500) {
					if (mvs != null) {
						circle(mvs);
						shoot(e);
					} else if (nearestAlly(Assault.class) != null) {
						circle(nearestAlly(Assault.class));
						shoot(e);
					} else {
						circle(mvm);
						shoot(e);
					}
				} else {
					moveTo(e);
					shoot(e);
				}
			}

		}
	}

	void circle(Unit u) {
		if (getDistance(u) > 180) {
			moveTo(u);
		} else if (getDistance(u) < 80) {
			turnTo(u);
			move(180);
		} else if (getDistance(u) < 160) {
			turnTo(u);
			move(((int) this.getAngleToward(this.getHomeBase().getCenterX(), this.getHomeBase().getCenterY())) + 100);
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
}
