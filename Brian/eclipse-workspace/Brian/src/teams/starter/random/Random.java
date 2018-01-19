package teams.starter.random;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Game;
import core.Utility;
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

public class Random extends Player {
	int count = 0;
	int r = 0;

	public Random(int team, Game g) throws SlickException {
		super(team, g);
		setName("Random");
		loadImageSet("frame");
	}

	public Raider buildRaider() throws SlickException {
		return new RandomRaider(this);
	}

	public Miner buildMiner() throws SlickException {
		return new RandomMiner(this);
	}

	public Assault buildAssault() throws SlickException {
		return new RandomAssault(this);
	}

	public Specialist buildSpecialist() throws SlickException {
		return new RandomSpecialist(this);
	}

	public Support buildSupport() throws SlickException {
		return new RandomSupport(this);
	}

	public void action() throws SlickException {

		// Research all the passive upgrades
		
		beginResearch(RaiderMissile.class);
		beginResearch(RaiderEngine.class);
		
		beginResearch(RaiderPierce.class);
		
		beginResearch(MinerLaser.class);
		beginResearch(MinerHull.class);
		

		// Always make 4 miners at the start
		if (countMyMiners() < 2) {
			addMinerToQueue();
		}
		if ((countEnemyMiners() - countNeutralMiners()) > countMyMiners() && countMyMiners() < 15) {
			addMinerToQueue();
		}
		addRaiderToQueue();

	}

	public Unit getMostVulerableEnemy(Class<? extends Unit> clazz) {

		Unit bestUnit = null;

		if (getEnemyUnits().isEmpty() || getEnemyUnitsExclude(Miner.class).isEmpty()) {
			return null;
		}

		ArrayList<Unit> units = getEnemyUnitsExclude(Miner.class);

		float totalX = 0;
		float totalY = 0;

		int averageY = 0;
		int averageX = 0;

		int maxDistance = 0;

		for (Unit u : units) {
			totalX += u.getCenterX();
			totalY += u.getCenterY();

			averageX = (int) (totalX / units.size());
			averageY = (int) (totalY / units.size());
		}

		for (Unit u : getEnemyUnits(clazz)) {
			if (u.getDistance(averageX, averageY) > maxDistance
					&& (u.countEnemiesInRadius(1200) - u.getEnemiesInRadius(1200, Miner.class).size()) < 2) {
				maxDistance = (int) u.getDistance(averageX, averageY);
				bestUnit = u;
			}
		}
		return bestUnit;
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

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
