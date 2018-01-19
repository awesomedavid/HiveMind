package teams.starter.random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Game;
import objects.units.Miner;
import objects.units.Raider;
import objects.units.Unit;

public class RandomRaider extends Raider {
	Random p;

	public RandomRaider(Random p) throws SlickException {
		super(p);
		this.p = p;
	}

	public void action() {
		if (p.countMyRaiders() > 13) {
			setOrder(Order.ATTACK);
		} else {
			moveTo(getHomeBase());
		}

	}

	@Override

	protected void attack() {
		Unit e = nearestEnemy();

		if (p.countEnemyUnits() - p.countEnemyMiners() > 5) {
			if (getDistance(e) < 2000 && p.countEnemyMiners() > 0) {
				if (e instanceof Miner) {
					moveTo(e);
					ability(e);
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

	@Override
	protected void defend() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void guard() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void rally() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void skirmish() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void special() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}
}
