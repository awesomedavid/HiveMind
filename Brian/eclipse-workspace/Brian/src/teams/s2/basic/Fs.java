package teams.s2.basic;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Game;
import core.Utility;
import objects.ambient.Asteroid;
import objects.ambient.HighYieldAsteroid;
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
import objects.upgrades.RaiderEngine;
import objects.upgrades.RaiderMissile;
import objects.upgrades.RaiderPierce;
import objects.upgrades.SpecialistKinetic;
import objects.upgrades.SpecialistReactor;
import objects.upgrades.SpecialistRift;
import objects.upgrades.SupportEnergy;
import objects.upgrades.SupportFix;
import scenario.Scenario;
import teams.s2.basic.Extras.MiningStuff.HighYieldMiningAsteroid;
import teams.s2.basic.Extras.MiningStuff.MiningAsteroid;
import teams.s2.basic.Extras.MiningStuff.MiningManager;
import teams.s2.basic.Extras.Squads.AssassinSquad;
import teams.s2.basic.Extras.Squads.MainArmy;
import teams.s2.basic.Extras.Squads.Squad;
import weapons.RaiderAttack;

public class Fs extends Player {

	static MiningManager miningManager;

	private Squad mainArmy;
	private Squad assassins;

	/**************** Constructor ****************/

	public Fs(int team, Game g) throws SlickException {
		super(team, g);

		setName("MyTeamName");
		loadImageSet("classic");

		miningManager = new MiningManager();

		mainArmy = new MainArmy(this);
		assassins = new AssassinSquad(this);

	}

	/**************** Action Method ****************/

	public void action() throws SlickException {
		// Determine the number of asteroids the initial miners go to and create ships
		// accordingly

		if (Game.getTime() == 1) {
			initializeExtras();
		}

		if (countMyMiners() < 4) {
			addMinerToQueue();
		} else {
			addRaiderToQueue();
		}

		if (Game.getTime() % 2 == 0) {
			assassins.action();
			mainArmy.action();
		}

		if (Game.getTime() % 10 == 0) {
			if (countMyUnits() < 10) {
				for (int i = 0; i < getMyUnits().size(); i++) {

					Unit a = getMyUnits().get(i);

					if (a instanceof FsRaider && !((FsRaider) a).isInSquad()) {
						((FsRaider) a).setInSquad(true);

						mainArmy.getUnits().add(a);
					}
					if (a instanceof FsAssault && !((FsAssault) a).isInSquad()) {
						((FsAssault) a).setInSquad(true);

						mainArmy.getUnits().add(a);
					}
					if (a instanceof FsSpecialist && !((FsSpecialist) a).isInSquad()) {
						((FsSpecialist) a).setInSquad(true);

						mainArmy.getUnits().add(a);
					}
				}
			} else {
				for (int i = 0; i < getMyUnits().size(); i++) {

					Unit a = getMyUnits().get(i);

					if (a instanceof FsRaider && !((FsRaider) a).isInSquad()) {
						((FsRaider) a).setInSquad(true);

						assassins.getUnits().add(a);
					}
				}
			}
		}

		// You can research new technologies. The classes are under
		// "Objects/Units/Upgrades"
		beginResearch(RaiderPierce.class);

		setMessageOne("Raiders: " + countMyRaiders());

		// For more example code, check out the other "starter" teams
	}

	/**************** Draw Method ****************/

	public void draw(Graphics g) {

		// This method allows you to draw things on the screen. It's only visible if you
		// enable
		// that player's drawings. Press 'q' to enable drawings for BLUE and 'e' for
		// RED.
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

	public void initializeExtras() {
		try {
			int length = Game.getAsteroids().size();
			for (int i = 0; i < length; i++) {

				if (Game.getAsteroids().get(i) instanceof HighYieldAsteroid) {

					MiningManager.getHighYieldMiningAsteroids()
							.add(new HighYieldMiningAsteroid((HighYieldAsteroid) Game.getAsteroids().get(i)));
				} else {
					miningManager.getMiningAsteroids().add(new MiningAsteroid(Game.getAsteroids().get(i)));
				}
			}
		} catch (Exception e) {
		}
	}

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

	public void addRaider() {
		try {
			addRaiderToQueue();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addAssault() {
		try {
			addAssaultToQueue();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addSpecialist() {
		try {
			addSpecialistToQueue();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Unit getNearestEnemy(Unit u) {
		if (getEnemies() == null || getEnemies().isEmpty())
			return null;

		float nearestDistance = Float.MAX_VALUE;
		Unit nearestTarget = null;

		for (Unit a : getEnemies()) {
			float d = Utility.distance(u, a);

			if (d < nearestDistance) {
				nearestDistance = d;
				nearestTarget = a;
			}
		}
		return nearestTarget;
	}

	public Unit getNearestEnemyExclude(Unit u, Class<? extends Unit> clazz) {
		if (getEnemies() == null || getEnemies().isEmpty())
			return null;

		float nearestDistance = Float.MAX_VALUE;
		Unit nearestTarget = null;

		for (Unit a : getEnemies()) {
			float d = Utility.distance(u, a);

			if (d < nearestDistance && !clazz.isAssignableFrom(a.getClass())) {
				nearestDistance = d;
				nearestTarget = a;
			}
		}
		return nearestTarget;
	}

	public Squad getSquadType(Unit u) {
		if (mainArmy.hasThisUnit(u)) {
			return mainArmy;
		} else {
			return assassins;
		}
	}

	public Squad getMainArmy() {
		return mainArmy;
	}

	public Squad getAssassins() {
		return assassins;
	}
}
