package teams.s2.basic;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Utility;
import core.Values;
import objects.base.Player;
import objects.units.Assault;
import objects.units.Miner;
import objects.units.Raider;
import objects.units.Unit;

public class FsRaider extends Raider {
	Fs p;

	private boolean inSquad;

	Color c;

	public FsRaider(Fs p) throws SlickException {
		super(p);
		this.p = p;

		setInSquad(false);
	}

	/***************** Action Method ***************/

	public void action() {

		// This method is called every frame, BEFORE the order method is called
		// moveTo(nearestEnemy());
		// moveTo(p.getNearestEnemyExclude(this, Miner.class));
		shoot(nearestEnemy());


	}

	/***************** Order Methods ***************/

	protected void attack() {
		// This method is called every frame while the unit's order is set to ATTACK

		// moveTo(nearestEnemy());
		shoot(nearestEnemy());

	}

	protected void defend() {
		// This method is called every frame while the unit's order is set to DEFEND
		moveTo(getHomeBase());
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

		if (p.getSquadType(this) == p.getMainArmy()) {

			c = new Color(255, 0, 255, 80);

		} else {

			c = new Color(255, 0, 0, 80);
		}
		g.setColor(c);

		g.drawOval(getCenterX() - Values.RAIDER_ATTACK_RANGE, getCenterY() - Values.RAIDER_ATTACK_RANGE, Values.RAIDER_ATTACK_RANGE * 2, Values.RAIDER_ATTACK_RANGE * 2);
	}

	public boolean isInSquad() {
		return inSquad;
	}

	public void setInSquad(boolean inSquad) {
		this.inSquad = inSquad;
	}
}
