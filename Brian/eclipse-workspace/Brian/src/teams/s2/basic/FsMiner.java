package teams.s2.basic;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Utility;

import objects.units.Miner;
import teams.s2.basic.Extras.MiningAsteroid;

public class FsMiner extends Miner {
	Fs p;

	MiningAsteroid a;

	private boolean inMiningQueue;

	/***************** Constructor ***************/

	public FsMiner(Fs p) throws SlickException {
		super(p);
		this.p = p;
	}

	/***************** Action Method ***************/

	public void action() {

		// This method is called every frame, BEFORE the order method is called
		
		mine();

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

		// This method allows you to draw things on the screen. It's only visible if you
		// enable
		// that player's drawings. Press 'q' to enable drawings for BLUE and 'e' for
		// RED.
	}

	public void mine() {
		if (isFull()) {
			if (isInMiningQueue()) {

				this.setInMiningQueue(false);
				a.removeFromMiningQueue(this);
				a.setOpen(true);
			}

			moveTo(getHomeBase());
		} else {
			if (!isInMiningQueue()) {
				setInMiningQueue(true);
				if (nearestOpenMiningAsteroid() != null) {
					a = nearestOpenMiningAsteroid();
					a.addToMiningQueue(this);
				}
				if (a.getCurMiners() == a.getMaxMiners()) {
					a.setOpen(false);
				}
			}
			if (isInMiningQueue()) {

				if (a.hasMinerals()) {
					moveTo(a.getAsteroid());
					startMine(a.getAsteroid());
				} else {
					this.setInMiningQueue(false);
					a.removeFromMiningQueue(this);
					a.setOpen(true);
				}
			}
		}
	}

	final public MiningAsteroid nearestOpenMiningAsteroid() {
		if (asteroids.isEmpty())
			return null;

		float nearestDistance = Float.MAX_VALUE;
		MiningAsteroid nearestTarget = null;

		for (MiningAsteroid a : Fs.miningManager.getMiningAsteroids()) {
			float d = Utility.distance(this, a);

			if (d < nearestDistance && a.isOpen() && a.hasMinerals()) {
				nearestDistance = d;
				nearestTarget = a;
			}
		}
		return nearestTarget;
	}

	final public MiningAsteroid nearestMiningAsteroid() {
		if (asteroids.isEmpty())
			return null;

		float nearestDistance = Float.MAX_VALUE;
		MiningAsteroid nearestTarget = null;

		for (MiningAsteroid a : Fs.miningManager.getMiningAsteroids()) {
			float d = Utility.distance(this, a);

			if (d < nearestDistance && a.hasMinerals()) {
				nearestDistance = d;
				nearestTarget = a;
			}
		}
		return nearestTarget;
	}

	public boolean isInMiningQueue() {
		return inMiningQueue;
	}

	public void setInMiningQueue(boolean inMiningQueue) {
		this.inMiningQueue = inMiningQueue;
	}
}
