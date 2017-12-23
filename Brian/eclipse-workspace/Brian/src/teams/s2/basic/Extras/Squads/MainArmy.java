package teams.s2.basic.Extras.Squads;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import core.Game;
import objects.base.Player;
import objects.units.Assault;
import objects.units.Miner;
import objects.units.Raider;
import objects.units.Specialist;
import objects.units.Unit;
import teams.s2.basic.Fs;
import teams.s2.basic.FsRaider;
import teams.s2.basic.FsSpecialist;
import teams.starter.swarm.Swarm;

public class MainArmy extends Squad{
	Fs p;

	public MainArmy(Fs p) throws SlickException {
		super(p);
		this.p = p;
	}

	public void action() {
		for(int i = 0; i < getUnits().size(); i++) {
			Unit a = getUnits().get(i);
			//a.moveTo(p.getNearestEnemyExclude(a, Miner.class));
			//a.moveTo(p.getMyBase());
		}
	}
}