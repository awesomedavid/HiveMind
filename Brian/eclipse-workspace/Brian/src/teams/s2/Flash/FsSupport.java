package teams.s2.Flash;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import objects.units.Assault;
import objects.units.BaseShip;
import objects.units.Miner;
import objects.units.Raider;
import objects.units.Specialist;
import objects.units.Support;
import objects.units.Unit;

public class FsSupport extends Support {
	Flash p;

	boolean beingHealed;

	Unit c = null;
	Unit u;

	public FsSupport(Flash p) throws SlickException {
		super(p);
		this.p = p;

	}

	/***************** Action Method ***************/

	public void action() {
		// This method is called every frame, BEFORE the relevant order method is called
		ArrayList<Unit> Units = getAlliesInRadius(450);

		u = nearestAllyExclude(Support.class);
		for (int i = 0; i < Units.size(); i++) {
			if (canHeal(Units.get(i)) && (Units.get(i).getCurHealth() / Units.get(i).getMaxHealth()) < (u.getCurHealth()
					/ u.getMaxHealth())) {
				u = Units.get(i);
			}
		}

		if (u != null) {
			shoot(u);
		}


		if (nearestAlly(Specialist.class) != null) {
			u = nearestAlly(Specialist.class);
		} else if (nearestAlly(Assault.class) != null) {
			u = nearestAlly(Assault.class);

		} else if (nearestAlly(Raider.class) != null) {
			u = nearestAlly(Raider.class);

		} else if (nearestAlly(Support.class) != null) {
			u = nearestAlly(Support.class);

		} else if (nearestAlly(Miner.class) != null) {
			u = nearestAlly(Miner.class);
		} else {
			u = nearestAlly();
		}

		for (int i = 0; i < Units.size(); i++) {
			if (!(Units.get(i) instanceof Miner) && !(Units.get(i) instanceof Support)
					&& canHeal(Units.get(i)) && (Units.get(i).getCurHealth()
							/ Units.get(i).getMaxHealth()) < (u.getCurHealth() / u.getMaxHealth())
					&& Units.get(i).getAlliesInRadius(1000, Support.class).size() == 0) {
				u = Units.get(i);
			}
		}
		if (p.getMyBase().getAngleToward(p.getEnemyBase().getX(), p.getEnemyBase().getY()) < 185) {
			moveTo(u.getCenterX() + 200, u.getCenterY());
		} else {
			if (u != null) {
				moveTo(u.getCenterX() - 200, u.getCenterY());
			}
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

		Color b = new Color(255, 0, 0);

		g.setColor(b);

		if (u != null) {

			g.drawLine(getX(), getY(), u.getX(), u.getY());
		}
	}

	public boolean isBeingHealed() {
		return beingHealed;
	}

	public void setBeingHealed(boolean beingHealed) {
		this.beingHealed = beingHealed;
	}

}
