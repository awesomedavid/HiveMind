package teams.s2.basic;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Utility;
import objects.base.Player;
import objects.units.Assault;
import objects.units.Raider;
import objects.units.Unit;

public class FsRaider extends Raider {
	Fs p;

	private boolean leader;

	public FsRaider(Fs p) throws SlickException {
		super(p);
		this.p = p;

		setLeader(false);
	}

	/***************** Action Method ***************/

	public void action() {

		// This method is called every frame, BEFORE the order method is called

	}

	/***************** Order Methods ***************/

	protected void attack() {
		// This method is called every frame while the unit's order is set to ATTACK

		moveTo(nearestEnemy());
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
	}

	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}
}
