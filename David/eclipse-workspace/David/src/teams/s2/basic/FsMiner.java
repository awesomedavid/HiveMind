package teams.s2.basic;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Utility;
import objects.units.Miner;
import objects.units.Unit;

public class FsMiner extends Miner {
	float timeHigh = 0;
	float timeNorm = 0;
	Fs p;

	private boolean inMiningQueue;

	/***************** Constructor ***************/

	public FsMiner(Fs p) throws SlickException {
		super(p);
		this.p = p;
	}

	/***************** Action Method ***************/
	
	public void action() {
		
		// This method is called every frame, BEFORE the order method is called
		Unit e = nearestEnemy();
		if(getDistance(e)<1055) {
			stopMine();
			moveTo(getHomeBase());
		}else {
		if (isFull()) {
			moveTo(getHomeBase());
		} else {
			checkTimes();
			if(nearestOpenHighYieldAsteroid() != null)
			{
			if(timeHigh < timeNorm)
			{
				moveTo(nearestOpenHighYieldAsteroid());
				startMine(nearestOpenHighYieldAsteroid());			
			}else {
				moveTo(nearestOpenAsteroid());
				startMine(nearestOpenAsteroid());		
			}
			}else {
				moveTo(nearestOpenAsteroid());
				startMine(nearestOpenAsteroid());		
				
			}
		}
		}
		//if(!isInMiningQueue())
		
		
	}
	public void checkTimes() {
		timeNorm = ((Utility.distance(this, nearestOpenAsteroid())/this.getMaxSpeed())*2)+((this.getCapacity()+this.getLoad())/this.getRate());
		timeHigh = ((Utility.distance(this, nearestOpenHighYieldAsteroid())/this.getMaxSpeed())*2)+((this.getCapacity()+this.getLoad())/(this.getRate()*2));
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

	public boolean isInMiningQueue() {
		return inMiningQueue;
	}

	public void setInMiningQueue(boolean inMiningQueue) {
		this.inMiningQueue = inMiningQueue;
	}
}
