package teams.neutral.hunters;

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

public class Hunter extends Player 
{
	boolean readyToHunt = false;
	
	public Hunter(int team, Game g) throws SlickException {
		super(team, g);
		setName("Collectors");
		loadImageSet("classic");
		
		
	}

	public Raider buildRaider() throws SlickException {
		return new HunterRaider(this);
	}

	public Miner buildMiner() throws SlickException {
		return new HunterMiner(this);
	}

	public Assault buildAssault() throws SlickException {
		return new HunterAssault(this);
	}

	public Specialist buildSpecialist() throws SlickException {
		return new HunterSpecialist(this);
	}

	public Support buildSupport() throws SlickException {
		return new HunterSupport(this);
	}

	public void action() throws SlickException 
	{
		readyToHunt = false;
		
		if (countMyMiners() < 8) {
			addMinerToQueue();
		}
		else if (countMyAssaults() < 4) 
		{
			addAssaultToQueue();
		} else if (countMySpecialists() < 2) 
		{
			addSpecialistToQueue();
		}
		else if (countMySupports() < 4) 
		{
			addSupportToQueue();
		}
		else if(countMyRaiders() < 6)
		{
			addRaiderToQueue();
		}
		else
		{
			readyToHunt = true;
		}
		
		
		
		
	}


	public void draw(Graphics g) 
	{

	}

}
