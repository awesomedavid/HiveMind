package teams.s2.Fs;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Game;
import core.Utility;
import objects.ambient.Asteroid;
import objects.base.Player;
import objects.units.Assault;
import objects.units.Miner;
import objects.units.Raider;
import objects.units.Specialist;
import objects.units.Support;
import objects.units.Unit;
import objects.units.Unit.Order;
import objects.upgrades.AssaultAegis;
import objects.upgrades.AssaultExplosive;
import objects.upgrades.AssaultShield;
import objects.upgrades.MinerHull;
import objects.upgrades.MinerLaser;
import objects.upgrades.MinerMine;
import objects.upgrades.RaiderEngine;
import objects.upgrades.RaiderMissile;
import objects.upgrades.RaiderPierce;
import objects.upgrades.SpecialistKinetic;
import objects.upgrades.SpecialistReactor;
import objects.upgrades.SpecialistRift;
import objects.upgrades.SupportEnergy;
import objects.upgrades.SupportFix;
import scenario.Scenario;
import teams.s2.Fs.Extras.MiningAsteroid;
import teams.s2.Fs.Extras.MiningManager;
import teams.s2.Fs.Extras.Squadron;
import teams.s2.Fs.Miners.FsMiner;
import teams.s2.Fs.Raiders.FsRaider;
import weapons.RaiderAttack;

public class Fs extends Player {
	private static MiningManager miningManager;

	private ArrayList<Squadron> squadrons;

	/**************** Constructor ****************/

	public Fs(int team, Game g) throws SlickException {
		super(team, g);
		setName("MyTeamName");
		loadImageSet("classic");

		setMiningManager(new MiningManager());

		squadrons = new ArrayList<Squadron>();

	}

	/**************** Action Method ****************/

	public void action() throws SlickException {
		setMessageOne("hi" + countMySupports());
		if (Game.getTime() == 1) {
			initializeExtras();
		}
		beginResearch(MinerMine.class);
		beginResearch(AssaultAegis.class);
		beginResearch(SpecialistReactor.class);
		beginResearch(AssaultShield.class);
		beginResearch(RaiderPierce.class);
		if (countMyMiners() < 2) {
			addMinerToQueue();
		}
		if (countEnemyMiners() > countMyMiners()) {
			addMinerToQueue();
		}
		if (countEnemyRaiders() > countMyAssaults() * 3) {
			addAssaultToQueue();
		}
		if (countEnemySpecialists() > countMyRaiders() / 3) {
			addRaiderToQueue();
		}
		if (countEnemyAssaults() > countMySpecialists()) {
			addSpecialistToQueue();
		}
		if (getMinerals() > 22) {
			if (countMyAssaults() > countMyRaiders() / 3) {
				addRaiderToQueue();
			} else if (countMyUnits() / 3 < countMySupports()) {
				addSupportToQueue();
			} else {
				addAssaultToQueue();
			}
		}
		// altRight
		if (Utility.distance(getMyBase(), getEnemyBase()) < 1000) {
			addAssaultToQueue();
		}

	}

	/**************** Draw Method ****************/

	public void draw(Graphics g) {

		// This method allows you to draw things on the screen. It's only visible if you
		// enable
		// that player's drawings. Press 'q' to enable drawings for BLUE and 'e' for
		// RED.

		Color c = new Color(255, 0, 0);
		g.setColor(c);

		// g.fillOval(0,0,500,500);

		if (getMostVulerableEnemy(Miner.class) != null) {

			g.fillOval(getMostVulerableEnemy(Miner.class).getX(), getMostVulerableEnemy(Miner.class).getY(), 500, 500);
		}
	}

	/*****************
	 * Build Methods ***************\
	 * 
	 * These methods determine what kind of unit is created when you build a unit.
	 * At the start, don't modify them. Later on, you can use them to situationally
	 * create alternate versions of each unit type with different behaviors.
	 * 
	 * \
	 ***********************************************/

	public Raider buildRaider() throws SlickException {
		return new FsRaider(this);
	}

	public Miner buildMiner() throws SlickException {
		return new FsMiner(this);
	}

	public Assault buildAssault() throws SlickException {
		return new FsAssault(this);
	}

	public Specialist buildSpecialist() throws SlickException {
		return new FsSpecialist(this);
	}

	public Support buildSupport() throws SlickException {
		return new FsSupport(this);
	}

	public void initializeExtras() {
		try {
			int length = Game.getAsteroids().size();
			for (int i = 0; i < length; i++) {
				getMiningManager().getMiningAsteroids().add(new MiningAsteroid(Game.getAsteroids().get(i)));

			}
		} catch (Exception e) {
		}
	}

	public Unit getMostVulerableEnemy(Class<? extends Unit> clazz) {

		int maxEnemies = 100;
		Unit bestUnit = null;

		if (getEnemyUnits().isEmpty()) {
			return null;
		}
		for (Unit u : getEnemyUnits()) {
			if (clazz.isAssignableFrom(u.getClass())) {
				if ((u.countEnemiesInRadius(750) - u.getEnemiesInRadius(750, Miner.class).size()) < maxEnemies) {
					bestUnit = u;
					maxEnemies = u.countEnemiesInRadius(750) - u.getEnemiesInRadius(750, Miner.class).size();
				}
			}
		}
		
		ArrayList <Unit> units = getEnemyUnitsExclude(Miner.class);
		
		float totalX = 0;
		float totalY = 0;
		
		for(Unit u: units) {
			totalX += u.getCenterX();
			totalY += u.getCenterY();
			
			int averageX = (int) (totalX / units.size());
			int averageY = (int) (totalY / units.size());
		}
		
		
		
		for (Unit u : getEnemyUnits()) {
			if (clazz.isAssignableFrom(u.getClass())) {
				if ((u.countEnemiesInRadius(750) - u.getEnemiesInRadius(750, Miner.class).size()) < maxEnemies) {
					bestUnit = u;
					maxEnemies = u.countEnemiesInRadius(750) - u.getEnemiesInRadius(750, Miner.class).size();
				}
			}
		}
		
		
		return bestUnit;
	}

	public ArrayList<Unit> getEnemyUnitsExclude(Class<? extends Unit> clazz) {

		ArrayList<Unit> units = new ArrayList<Unit>();
		
		if (clazz != Specialist.class) {
			for (Unit u : getMyUnits(Specialist.class)) {
				units.add(u);
			}
		}
		if (clazz != Raider.class) {
			for (Unit u : getMyUnits(Raider.class)) {
				units.add(u);
			}
		}
		if (clazz != Assault.class) {
			for (Unit u : getMyUnits(Assault.class)) {
				units.add(u);
			}
		}
		if (clazz != Support.class) {
			for (Unit u : getMyUnits(Support.class)) {
				units.add(u);
			}
		}
		if (clazz != Miner.class) {
			for (Unit u : getMyUnits(Miner.class)) {
				units.add(u);
			}
		}
		return units;
	}

	public static MiningManager getMiningManager() {
		return miningManager;
	}

	public static void setMiningManager(MiningManager miningManager) {
		Fs.miningManager = miningManager;
	}
}
