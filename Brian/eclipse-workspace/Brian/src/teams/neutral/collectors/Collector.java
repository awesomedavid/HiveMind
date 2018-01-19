package teams.neutral.collectors;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Game;
import core.Utility;
import core.Values;
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
import ui.display.Alert;

public class Collector extends Player 
{
	
	public Collector(int team, Game g) throws SlickException {
		super(team, g);
		setName("Collectors");
		loadImageSet("classic");
	}

	public Raider buildRaider() throws SlickException {
		return new CollectorRaider(this);
	}

	public Miner buildMiner() throws SlickException {
		return new CollectorMiner(this);
	}

	public Assault buildAssault() throws SlickException {
		return new CollectorAssault(this);
	}

	public Specialist buildSpecialist() throws SlickException {
		return new CollectorSpecialist(this);
	}

	public Support buildSupport() throws SlickException {
		return new CollectorSupport(this);
	}

	public void action() throws SlickException 
	{
		// not working?
		beginResearch(MinerLaser.class);
		beginResearch(MinerHull.class);
		beginResearch(MinerMine.class);

		if (countMyMiners() > countMySpecialists() * 7) {
			addSpecialistToQueue();
		} else if (countMyMiners() > countMySupports() * 7) {
			addSupportToQueue();
		} else {
			addMinerToQueue();
		}
		
	}


	public void draw(Graphics g) 
	{

	}

}
