package weapons;

import animations.projectiles.AnimRaiderAttack;
import core.Game;
import core.Values;
import effects.Damage;
import effects.DamageIgnoreArmor;
import objects.units.Unit;
import objects.upgrades.RaiderPierce;
import ui.sound.Audio;

public class RaiderAttack extends Weapon 
{	
	public RaiderAttack(Unit owner) 
	{
		super(owner);

		active = true;
		damage = Values.RAIDER_ATTACK_DAMAGE;
		speed = Values.RAIDER_ATTACK_SPEED;
		range = Values.RAIDER_ATTACK_RANGE;
		cooldown = Values.RAIDER_ATTACK_COOLDOWN;
	}
	
	public boolean use(Unit a) 
	{
		if (canShoot(a)) 
		{
			owner.reverse(Values.RAIDER_ATTACK_RECOIL);
			Audio.playMG(owner.getPosition());
			
			delay = getDelay(a);

			if (owner.getOwner().hasResearch(RaiderPierce.class))
			{
				float targetArmor = a.getCurArmor() * (1.0f - Values.RAIDER_UPGRADE_PIERCE_PERCENT);
				float actualDamage = damage * Values.RAIDER_UPGRADE_PIERCE_DAMAGE - targetArmor;
				
				if(actualDamage > 0)
				{
					a.addEffect(new DamageIgnoreArmor(a, owner.getOwner(), delay, actualDamage));
				}
			}
			else
			{
				a.addEffect(new Damage(a, owner.getOwner(), delay, damage));
			}
			
			// Basic Damage

			owner.actionComplete();
			shotTimer = cooldown;
			animation(a, getDelay(a));

			return true;
		} else {
			return false;
		}


	}

	public void animation(Unit a, int delay) {
		Game.addAnimation(new AnimRaiderAttack(owner, a, speed, delay));
		
	}

}
