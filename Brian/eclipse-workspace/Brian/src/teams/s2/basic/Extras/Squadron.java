package teams.s2.basic.Extras;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import core.Game;
import objects.base.Player;
import objects.units.Assault;
import objects.units.Raider;
import objects.units.Specialist;
import objects.units.Unit;
import teams.s2.basic.Fs;
import teams.starter.swarm.Swarm;

public class Squadron{
	private final static int SQUAD_RAIDER_NUM = 4;
	private final static int SQUAD_ASSAULT_NUM = 2;
	private final static int SQUAD_SPECIALIST_NUM = 1;

	private ArrayList<Unit> units;

	public Squadron(int num1, int num2, int num3) throws SlickException {		
		//create();	
	}
	
	public void create() {
		//Fs.addGroup(SQUAD_RAIDER_NUM, SQUAD_ASSAULT_NUM, SQUAD_SPECIALIST_NUM);
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}

	public ArrayList<Raider> getRaiders() {
		ArrayList<Raider> raiders = new ArrayList<Raider>();
		for (Unit u : getUnits()) {
			if (u instanceof Raider) {
				raiders.add((Raider) u);
			}
		}
		return raiders;
	}

	public ArrayList<Assault> getAssaults() {
		ArrayList<Assault> assaults = new ArrayList<Assault>();
		for (Unit u : getUnits()) {
			if (u instanceof Raider) {
				assaults.add((Assault) u);
			}
		}
		return assaults;
	}

	public ArrayList<Specialist> getSpecialists() {
		ArrayList<Specialist> specialists = new ArrayList<Specialist>();
		for (Unit u : getUnits()) {
			if (u instanceof Specialist) {
				specialists.add((Specialist) u);
			}
		}
		return specialists;
	}
}