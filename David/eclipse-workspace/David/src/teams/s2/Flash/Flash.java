package teams.s2.Flash;

import java.util.ArrayList;

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
import teams.s2.Flash.Extras.MiningAsteroid;
import teams.s2.Flash.Extras.MiningManager;
import teams.s2.Flash.Extras.Squadron;
import weapons.RaiderAttack;

public class Flash extends Player {
	private static MiningManager miningManager;

	private ArrayList<Squadron> squadrons;

	/**************** Constructor ****************/

	public Flash(int team, Game g) throws SlickException {
		super(team, g);
		setName("FlashStick");
		loadImageSet("classic");

		setMiningManager(new MiningManager());

		squadrons = new ArrayList<Squadron>();

	}

	/**************** Action Method ****************/

	public void action() throws SlickException {
		setMessageOne("NumSup "+countMySupports());
		setMessageTwo(";)");
		if (Game.getTime() == 1) {
			initializeExtras();
		}
		beginResearch(AssaultAegis.class);
		beginResearch(MinerLaser.class);				
		beginResearch(RaiderPierce.class);
		beginResearch(MinerHull.class);
		beginResearch(AssaultShield.class);		
		beginResearch(SpecialistReactor.class);			
		beginResearch(SupportEnergy.class);
		beginResearch(RaiderEngine.class);
		beginResearch(SpecialistKinetic.class);
		beginResearch(SupportFix.class);
		beginResearch(AssaultExplosive.class);
		if (countMyMiners() < 2) {
			addMinerToQueue();
		}
		if ((countEnemyMiners()-countNeutralMiners()) > countMyMiners()) {
			addMinerToQueue();
		}
		if ((countEnemyRaiders()-countNeutralRaiders()) > countMyAssaults() * 3) {
			addAssaultToQueue();
		}		
		if ((countEnemySpecialists()-countNeutralSpecialists()) > countMyRaiders() / 3) {
			addRaiderToQueue();
		}	
		if ((countEnemyAssaults()-countNeutralAssaults()) > countMySpecialists()) {
			addSpecialistToQueue();
		}
		if (getMinerals()> 23) {			
			if((countMyUnits()-countMyMiners())/3>countMySupports()) {
				addSupportToQueue();							
			}else if(countMyAssaults() < countMyRaiders()/3) {
				addAssaultToQueue();
			}else {
				addRaiderToQueue();
			}
		}
		
//		if (getMyBase().getDistance(getEnemyBase())<1000) {
//			addAssaultToQueue();
//		}

	}

	/**************** Draw Method ****************/

	public void draw(Graphics g) {

		// This method allows you to draw things on the screen. It's only visible if you
		// enable
		// that player's drawings. Press 'q' to enable drawings for BLUE and 'e' for
		// RED.
	}
	public ArrayList<Unit> getEnemyUnitsExclude(Class<? extends Unit> clazz) {

		ArrayList<Unit> units = new ArrayList<Unit>();

		if (clazz != Specialist.class && !getEnemyUnits(Specialist.class).isEmpty()) {
			for (Unit u : getEnemyUnits(Specialist.class)) {
				units.add(u);
			}
		}
		if (clazz != Raider.class && !getEnemyUnits(Raider.class).isEmpty()) {
			for (Unit u : getEnemyUnits(Raider.class)) {
				units.add(u);
			}
		}
		if (clazz != Assault.class && !getEnemyUnits(Assault.class).isEmpty()) {
			for (Unit u : getEnemyUnits(Assault.class)) {
				units.add(u);
			}
		}
		if (clazz != Support.class && !getEnemyUnits(Support.class).isEmpty()) {
			for (Unit u : getEnemyUnits(Support.class)) {
				units.add(u);
			}
		}
		if (clazz != Miner.class && !getEnemyUnits(Miner.class).isEmpty()) {
			for (Unit u : getEnemyUnits(Miner.class)) {
				units.add(u);
			}
		}
		return units;
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

	public static MiningManager getMiningManager() {
		return miningManager;
	}

	public static void setMiningManager(MiningManager miningManager) {
		Flash.miningManager = miningManager;
	}
}
