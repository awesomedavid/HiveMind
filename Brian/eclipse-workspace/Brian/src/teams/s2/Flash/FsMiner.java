package teams.s2.Flash;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Utility;
import objects.units.BaseShip;
import objects.units.Miner;
import objects.units.Unit;
import teams.s2.Flash.Extras.HighYieldMiningAsteroid;
import teams.s2.Flash.Extras.MiningAsteroid;
import teams.s2.Flash.FsMiner;

public class FsMiner extends Miner {

	Flash p;

	MiningAsteroid a;

	private boolean inMiningQueue;

	float timeHigh = 0;
	float timeNorm = 0;
	boolean beingHealed;

	/***************** Constructor ***************/

	public FsMiner(Flash p) throws SlickException {
		super(p);
		this.p = p;
	}

	/***************** Action Method ***************/

	public void action() {

		// This method is called every frame, BEFORE the order method is called
		Unit e = nearestEnemyExclude(BaseShip.class);
		if (e == null) {
			mine();
		} else {
			if (getDistance(e) < 1155 && !(e instanceof Miner)) {
				stopMine();
				moveTo(getHomeBase());
			} else if (getDistance(e) < 1155 && e instanceof Miner) {
			} else {
				mine();
			}
			shoot(e);
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

	public void mine() {
		if (isFull()) {
			if (isInMiningQueue()) {

				this.setInMiningQueue(false);
				a.removeFromMiningQueue(this);
				a.setOpen(true);
			}

			moveTo(getHomeBase());
		} else if (bestAsteroid() != null) {

			if (!isInMiningQueue()) {
				setInMiningQueue(true);

				a = bestAsteroid();
				a.addToMiningQueue(this);

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

		} else {
			moveTo(nearestOpenAsteroid());
			startMine(nearestOpenAsteroid());
		}
	}

	final public MiningAsteroid nearestOpenMiningAsteroid() {
		if (asteroids.isEmpty())
			return null;

		float nearestDistance = Float.MAX_VALUE;
		MiningAsteroid nearestTarget = null;

		for (MiningAsteroid a : Flash.getMiningManager().getMiningAsteroids()) {
			float d = Utility.distance(this, a);

			if (d < nearestDistance && a.isOpen() && a.hasMinerals() && a.getAsteroid().hasMiningSlots()) {
				nearestDistance = d;
				nearestTarget = a;
			}
		}
		return nearestTarget;
	}

	final public HighYieldMiningAsteroid nearestOpenHighYieldMiningAsteroid() {
		if (asteroids.isEmpty())
			return null;

		float nearestDistance = Float.MAX_VALUE;
		HighYieldMiningAsteroid nearestTarget = null;

		for (HighYieldMiningAsteroid a : Flash.getMiningManager().getHighYieldMiningAsteroids()) {
			float d = Utility.distance(this, a);

			if (d < nearestDistance && a.isOpen() && a.hasMinerals() && a.getAsteroid().hasMiningSlots()) {
				nearestDistance = d;
				nearestTarget = a;
			}
		}
		return nearestTarget;
	}

	final public MiningAsteroid bestAsteroid() {

		if (nearestOpenHighYieldMiningAsteroid() == null && nearestOpenMiningAsteroid() == null) {

			return null;

		} else if (nearestOpenHighYieldMiningAsteroid() == null) {

			return nearestOpenMiningAsteroid();

		} else if (nearestOpenMiningAsteroid() == null) {

			return nearestOpenHighYieldMiningAsteroid();
		}

		checkTimes();

		if (timeHigh < timeNorm) {
			return nearestOpenHighYieldMiningAsteroid();
		}
		return nearestOpenMiningAsteroid();
	}

	public void checkTimes() {
		timeNorm = ((Utility.distance(this, nearestOpenMiningAsteroid()) / this.getMaxSpeed()) * 2)
				+ ((this.getCapacity() + this.getLoad()) / this.getRate());
		timeHigh = ((Utility.distance(this, nearestOpenHighYieldMiningAsteroid()) / this.getMaxSpeed()) * 2)
				+ ((this.getCapacity() + this.getLoad()) / (this.getRate() * 2));
	}

	public boolean isInMiningQueue() {
		return inMiningQueue;
	}

	public void setInMiningQueue(boolean inMiningQueue) {
		this.inMiningQueue = inMiningQueue;
	}

	public boolean isBeingHealed() {
		return beingHealed;
	}

	public void setBeingHealed(boolean beingHealed) {
		this.beingHealed = beingHealed;
	}
}
