package teams.s2.basic.Extras.Squads;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import objects.units.Assault;
import objects.units.Raider;
import objects.units.Specialist;
import objects.units.Unit;
import teams.s2.basic.Fs;

public abstract class Squad {

	Fs p;

	private ArrayList<Unit> units;

	public Squad(Fs p) throws SlickException {

		this.p = p;
		units = new ArrayList<Unit>();
	}

	abstract public void action();

	public void add(Unit u) {
		units.add(u);
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
	
	public boolean hasThisUnit(Unit u) {
		for(int i = 0; i < getUnits().size(); i++) {
			if(getUnits().get(i) == u) {
				return true;
			}
		}
		return false;
	}
}
