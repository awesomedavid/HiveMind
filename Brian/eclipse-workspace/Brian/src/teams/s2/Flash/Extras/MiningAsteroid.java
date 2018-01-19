package teams.s2.Flash.Extras;

import org.newdawn.slick.SlickException;

import objects.ambient.Ambient;
import objects.ambient.Asteroid;
import objects.base.Player;
import objects.units.Miner;

public class MiningAsteroid extends Ambient {

	protected Miner[] minersInQueue;

	private Asteroid asteroid;
	private boolean open;

	public MiningAsteroid(Asteroid a) throws SlickException {

		super(a.getX(), a.getY());
		
		setOpen(true);

		asteroid = a;
		minersInQueue = new Miner[a.getMaxMiners()];
	}

	public void addToMiningQueue(Miner h) {
		for (int i = 0; i < minersInQueue.length; i++) {
			if (minersInQueue[i] == null) {
				minersInQueue[i] = h;
				return;
			}
		}
	}

	public void removeFromMiningQueue(Miner h) {
		for (int i = 0; i < minersInQueue.length; i++) {
			if (minersInQueue[i] == h) {
				minersInQueue[i] = null;
				return;
			}
		}
	}
	
	public int getMaxMiners() {
		return getAsteroid().getMaxMiners();
	}
	
	public int getCurMiners() {
		int count = 0;
		for (int i = 0; i < minersInQueue.length; i++) {
			if (minersInQueue[i] != null)
				count++;
		}

		return count;
	}	

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean hasMinerals() {
		return asteroid.hasMinerals();
	}

	public Asteroid getAsteroid() {
		return asteroid;
	}
}
