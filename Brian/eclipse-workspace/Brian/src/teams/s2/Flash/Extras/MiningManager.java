package teams.s2.Flash.Extras;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class MiningManager {
	static ArrayList<MiningAsteroid> miningAsteroids;
	static ArrayList<HighYieldMiningAsteroid> highYieldMiningAsteroids;

	
	public MiningManager() throws SlickException{
		
		miningAsteroids = new ArrayList<MiningAsteroid>();
		highYieldMiningAsteroids = new ArrayList<HighYieldMiningAsteroid>();
	}
	
	public static ArrayList<HighYieldMiningAsteroid> getHighYieldMiningAsteroids() {
		return highYieldMiningAsteroids;
	}

	public static void setHighYieldMiningAsteroids(ArrayList<HighYieldMiningAsteroid> highYieldMiningAsteroids) {
		MiningManager.highYieldMiningAsteroids = highYieldMiningAsteroids;
	}

	public ArrayList<MiningAsteroid> getMiningAsteroids() {
		return miningAsteroids;
	}

	public void setMiningAsteroids(ArrayList<MiningAsteroid> miningAsteroids) {
		MiningManager.miningAsteroids = miningAsteroids;
	}
}
