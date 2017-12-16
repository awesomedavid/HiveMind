package teams.s2.basic;

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
import teams.s2.basic.Extras.MiningAsteroid;
import teams.s2.basic.Extras.MiningManager;
import weapons.RaiderAttack;

public class Fs extends Player {

	static MiningManager miningManager;

	/**************** Constructor ****************/

	public Fs(int team, Game g) throws SlickException {
		super(team, g);
		setName("MyTeamName");
		loadImageSet("classic");

		miningManager = new MiningManager();

	}

	/**************** Action Method ****************/

	public void action() throws SlickException {

		if (Game.getTime() == 1) {
			initializeExtras();
		}

		// You can research new technologies. The classes are under
		// "Objects/Units/Upgrades"
		beginResearch(RaiderPierce.class);

		// miningAsteroids.add(new MiningAsteroid(Game.getAsteroids().get(2)));

		// To build a unit, use the relevant addUnitToQueue method.
		// It will only be added to your build queue if you can afford it.

		if (countMyMiners() < 100) {
			addMinerToQueue();
		} else {
			addRaiderToQueue();
		}

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
		try {
			int length = Game.getAsteroids().size();
			for (int i = 0; i < length; i++) {
				miningManager.getMiningAsteroids().add(new MiningAsteroid(Game.getAsteroids().get(i)));
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

}
