package teams.testCopy;

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
import objects.upgrades.RaiderEngine;
import objects.upgrades.RaiderMissile;
import objects.upgrades.RaiderPierce;
import objects.upgrades.SpecialistKinetic;
import objects.upgrades.SpecialistReactor;
import objects.upgrades.SpecialistRift;
import objects.upgrades.SupportEnergy;
import objects.upgrades.SupportFix;
import scenario.Scenario;
import teams.s2.basic.Extras.MiningStuff.MiningAsteroid;
import teams.s2.basic.Extras.MiningStuff.MiningManager;
import weapons.RaiderAttack;

public class Fs2 extends Player {

	static MiningManager miningManager;

	/**************** Constructor ****************/

	public Fs2(int team, Game g) throws SlickException {
		super(team, g);
		setName("MyTeamName");
		loadImageSet("classic");

		//miningManager = new MiningManager();

	}

	/**************** Action Method ****************/

	public void action() throws SlickException {
		
		// Determine the number of asteroids the initial miners go to and create ships accordingly

		if (Game.getTime() == 1) {
			initializeExtras();
		}

		// You can research new technologies. The classes are under
		// "Objects/Units/Upgrades"
		beginResearch(RaiderPierce.class);

		// miningAsteroids.add(new MiningAsteroid(Game.getAsteroids().get(2)));

		// To build a unit, use the relevant addUnitToQueue method.
		// It will only be added to your build queue if you can afford it.

//		if (countMyMiners() < 4) {
//			addMinerToQueue();
//		} else {
//			addRaiderToQueue();
//		}
		addMinerToQueue();

		// Unit a = (getMyUnits(FsRaider.class).get(0));

		// a.setOrder(Order.ATTACK);

		// You can set up to three messages
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
		//
	}

	public Raider buildRaider() throws SlickException {
		return new Fs2Raider(this);
	}

	public Miner buildMiner() throws SlickException {
		return new Fs2Miner(this);
	}

	public Assault buildAssault() throws SlickException {
		return new Fs2Assault(this);
	}

	public Specialist buildSpecialist() throws SlickException {
		return new Fs2Specialist(this);
	}

	public Support buildSupport() throws SlickException {
		return new Fs2Support(this);
	}

}
