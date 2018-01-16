package teams.s2.Flash;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import objects.units.Assault;
import objects.units.Miner;
import objects.units.Raider;
import objects.units.Specialist;
import objects.units.Support;
import objects.units.Unit;

public class FsSupport extends Support {
	Flash p;
	Unit u = nearestAlly();
//	Unit s = nearestAlly();

	public Unit getU() {
		return u;
	}

	public void setU(Unit u) {
		this.u = u;
	}

	public FsSupport(Flash p) throws SlickException {
		super(p);
		this.p = p;
	}

	/***************** Action Method ***************/

	public void action() {
		// This method is called every frame, BEFORE the relevant order method is called
		ArrayList<Unit> Units = getOwner().getMyUnits();
		ArrayList<Unit> Supports = getOwner().getMyUnits(Support.class);
		for (int i = 0; i < Units.size(); i++) {
			if (canHeal(Units.get(i)) && (Units.get(i).getCurHealth() / Units.get(i).getMaxHealth()) < (u.getCurHealth()
					/ u.getMaxHealth())) {
				u = Units.get(i);
			}
		}
//		for (int i = 0; i < Supports.size(); i++) {
//			if (Supports.get(i) instanceof FsSupport) {
//				
//			}
//		}

		moveTo(u.getX(), u.getY());
		if (u != null && u.isDamaged()) {
			shoot(u);
		}
	}

	/***************** Order Methods ***************/

	protected void attack() {
		// This method is called every frame while the unit's order is set to ATTACK
	}

	protected void defend() {
		// This method is called every frame while the unit's order is set to DEFEND
	}

	protected void guard() {
		// This method is called every frame while the unit's order is set to GUARD
	}

	protected void rally() {
		// This method is called every frame while the unit's order is set to RALLY
	}

	protected void skirmish() {
		// This method is called every frame while the unit's order is set to SKIRMISH
	}

	protected void special() {
		// This method is called every frame while the unit's order is set to SPECIAL
	}

	protected void run() {
		// This method is called every frame while the unit's order is set to RUN
	}

	/***************** DRAW Methods ***************/

	public void draw(Graphics g) {

		// This method allows you to draw things on the screen. It's only visible if you
		// enable
		// that player's drawings. Press 'q' to enable drawings for BLUE and 'e' for
		// RED.
	}

}
