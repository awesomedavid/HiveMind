package weapons;

import org.newdawn.slick.geom.Point;

import animations.projectiles.AnimTurretAttack;
import core.Game;
import core.Values;
import objects.units.Unit;
import ui.sound.Audio;

public class TurretAttack extends Weapon {

	public TurretAttack(Unit owner) {
		super(owner);

		active = true;
		damage = Values.TURRET_ATTACK_DAMAGE;
		speed = Values.TURRET_ATTACK_SPEED;
		range = Values.TURRET_ATTACK_RANGE;
		cooldown = Values.TURRET_ATTACK_COOLDOWN;

	}
	
	public void animation(Unit a, int delay) {
		Game.addAnimation(new AnimTurretAttack(owner, a, speed, delay));
		Audio.playMG(owner.getPosition());
	}

}
