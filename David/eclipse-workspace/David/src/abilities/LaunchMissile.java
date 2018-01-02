package abilities;

import org.newdawn.slick.Color;

import core.Values;
import objects.units.Raider;
import objects.units.Unit;

public class LaunchMissile extends Ability 
{
	final int RESTOCK_TIME = 750;
	int restockTimer = RESTOCK_TIME;

	public LaunchMissile(Unit owner)
	{
		super(owner);
		charges = Values.RAIDER_UPGRADE_MISSILE_CHARGES;
	}

	public void use(Unit u)
	{	
		if(charges > 0 && owner.getDistance(u) < Values.RAIDER_UPGRADE_MISSILE_RANGE)
		{
			owner.getOwner().spawnMissile(owner, u);
			charges--;
		}
	}
	
	public void update()
	{
		super.update();
		
		if(!nearBase())
		{
			restockTimer = 0;
		}
		
		if(hasMissile())
		{
			return;
		}
		
		if(restockTimer < RESTOCK_TIME && nearBase())
		{
			restockTimer++;
		}
		
		if(restockTimer == RESTOCK_TIME)
		{
			charges++;
			restockTimer = 0;
			owner.launch();
		}
	}
	
	public float getRestockProgress()
	{
		return (float) restockTimer / (float) RESTOCK_TIME;
	}
	
	public boolean nearBase()
	{
			return owner.getDistance(owner.getHomeBase()) <= Values.ASTROID_BASE_SHIP_DOCK_RANGE + 2;
	}
	
	public boolean hasMissile()
	{
		return charges > 0;
	}


}
