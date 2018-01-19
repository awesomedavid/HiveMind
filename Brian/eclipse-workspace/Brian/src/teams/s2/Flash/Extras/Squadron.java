package teams.s2.Flash.Extras;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import core.Game;
import objects.base.Player;
import objects.units.Assault;
import objects.units.Raider;
import objects.units.Specialist;
import objects.units.Unit;
import teams.s2.Flash.Flash;
import teams.s2.Flash.FsRaider;
import teams.starter.swarm.Swarm;

public class Squadron {
	private final static int SQUAD_RAIDER_NUM = 4;
	private final static int SQUAD_ASSAULT_NUM = 2;
	private final static int SQUAD_SPECIALIST_NUM = 1;

	Flash p;

	private ArrayList<Unit> units;

	public Squadron(Flash p) throws SlickException {

		this.p = p;
		units = new ArrayList<Unit>();
		create();
		
		
	}

	public void create() {
		for (int i = 0; i < SQUAD_RAIDER_NUM; i++) {
			//p.addRaider();
		}
		for (int i = 0; i < SQUAD_ASSAULT_NUM; i++) {
			//p.addAssault();
		}
		for (int i = 0; i < SQUAD_SPECIALIST_NUM; i++) {
			//p.addSpecialist();
		}
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
			if (u instanceof Assault) {
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
