package teams.s2.basic.Extras;

import org.newdawn.slick.SlickException;

import objects.ambient.Asteroid;
import objects.units.Miner;

public class MiningAsteroid extends Asteroid{
	
	protected Miner[] minersInQueue;

	public MiningAsteroid(float x, float y, float xSpeed, float ySpeed, int size) throws SlickException {
		super(x, y, xSpeed, ySpeed, size);
		
		minersInQueue = new Miner[size];
		// TODO Auto-generated constructor stub
	}
	
	public void addToMiningQueue(Miner h) {
		for (int i = 0; i < minersInQueue.length; i++) {
			if (minersInQueue[i] == null) {
				minersInQueue[i] = h;
				return;
			}
		}
	}

}
