package teams.s2.basic.Extras;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class MiningManager {
	static ArrayList<MiningAsteroid> miningAsteroids;
	
	public MiningManager() throws SlickException{
		
		miningAsteroids = new ArrayList<MiningAsteroid>();
	}
	
	public ArrayList<MiningAsteroid> getMiningAsteroids() {
		return miningAsteroids;
	}

	public void setMiningAsteroids(ArrayList<MiningAsteroid> miningAsteroids) {
		MiningManager.miningAsteroids = miningAsteroids;
	}
}
