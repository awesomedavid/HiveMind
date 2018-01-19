package objects.units;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

import abilities.LaunchMissile;
import animations.AnimationSpark;
import core.Game;
import core.Utility;
import core.Values;
import objects.base.Player;
import objects.upgrades.AssaultExplosive;
import objects.upgrades.RaiderEngine;
import objects.upgrades.RaiderMissile;
import ui.display.Camera;
import ui.display.Images;
import weapons.RaiderAttack;

public abstract class Raider extends Unit {
	private RaiderAttack basicAttack;
	boolean engineUpgraded = false;
	boolean missileUpgraded = false;
boolean pierceUpgraded = false;

	public Raider(Player p) throws SlickException {
		super(p);

		sheet = p.getImageRaider();
		image = sheet.getSprite(0, team);
		timer = (int) (Math.random() * 1000);
		theta = (int) (Math.random() * 360);

		basicAttack = new RaiderAttack(this);

		dodgeChance = Values.RAIDER_DODGE_CHANCE;
		curHealth = Values.RAIDER_HEALTH;
		maxHealth = Values.RAIDER_HEALTH;
		maxSpeed = Values.RAIDER_SPEED;
		acceleration = Values.RAIDER_ACCELERATION;
		value = Values.RAIDER_COST;
		combatValue = value;
		
		// Centering Spawn
		this.w = (int) (image.getWidth() * scale);
		this.h = (int) (image.getWidth() * scale);
		this.x = x - w;
		this.y = y - h;
	}



	public float getRange() 
	{
		if(inNebula()) return Values.CONCEALMENT_RANGE;
		else return basicAttack.getRange();
	}


	public void update() {
		super.update();

		if (!engineUpgraded && getOwner().hasResearch(RaiderEngine.class)) {
			maxSpeed = Values.RAIDER_SPEED * Values.RAIDER_UPGRADE_ENGINE_SPEED_MULTIPLIER;
			acceleration =  Values.RAIDER_ACCELERATION * Values.RAIDER_UPGRADE_ENGINE_ACCELERATION_MULTIPLIER;
			dodgeChance = RAIDER_UPGRADE_ENGINE_DODGE_CHANCE;
			engineUpgraded = true;
			combatValue = combatValue * Values.COMBAT_VALUE_UPGRADE_MULTIPLIER;
		}

		if(!missileUpgraded &&  getOwner().hasResearch(RaiderMissile.class))
		{
			ability = new LaunchMissile(this);
			missileUpgraded = true;
			combatValue = combatValue * Values.COMBAT_VALUE_UPGRADE_MULTIPLIER;

		}
		
		if(!pierceUpgraded && getOwner().hasResearch(AssaultExplosive.class))
		{
			basicAttack.setRange(Values.RAIDER_ATTACK_RANGE + Values.RAIDER_UPGRADE_PIERCE_RANGE_BONUS);
			pierceUpgraded = true;	
			combatValue = combatValue * Values.COMBAT_VALUE_UPGRADE_MULTIPLIER;
		}
		
		
//		if(hasMissile())
//		{
//			setHighlight(true);
//		}
//		else
//		{
//			setHighlight(false);
//		}
		
		basicAttack.update();

		if (canAct()) {
			action();
			if (getOrder().equals(Order.ATTACK))
				attack();
			else if (getOrder().equals(Order.DEFEND))
				defend();
			else if (getOrder().equals(Order.GUARD))
				guard();
			else if (getOrder().equals(Order.RALLY))
				rally();
			else if (getOrder().equals(Order.RUN))
				run();
			else if (getOrder().equals(Order.SKIRMISH))
				skirmish();
			else if (getOrder().equals(Order.SPECIAL))
				special();

		}
	}

	public void shoot(Unit u) {
		if (u == null)
			return;
		turnTo(u);
		basicAttack.use(u);
	}

	public void shoot() {
		shoot(getTargetUnit());
		
	
	}

	public boolean hasMissile()
	{
		return ability != null && ability instanceof LaunchMissile && ((LaunchMissile) ability).hasMissile();
	}

	// ability is a missile

	public void ability()
	{
		if(ability instanceof LaunchMissile && getTargetUnit() != null)
		{
			((LaunchMissile) ability).use(getTargetUnit());
		}
	}

	public void ability(Unit u)
	{
		if(ability instanceof LaunchMissile && u != null)
		{
			((LaunchMissile) ability).use(u);
		}
	}

	final public void render(Graphics g) {
		

		super.render(g);
	
		if (isAlive() && Camera.getZoom() >= Camera.HEALTHBAR_ZOOM_THRESHOLD && getRestockProgress() > 0 ) 
		{
			g.setColor(new Color(120, 120, 65));
			g.fillRect(x, y - 20, image.getWidth() * scale, 7);

			g.setColor(new Color(255, 255, 100));
			g.fillRect(x, y - 19, getRestockProgress() * image.getWidth() * scale, 5);
			
		}
		
		
		if(hasMissile() && Camera.getZoom() >= Camera.HEALTHBAR_ZOOM_THRESHOLD)
		{
			g.setLineWidth(1);
			g.setColor(getColor());
			g.drawOval(x-w/2, y -  h/2, w*2, h*2);
			
//			g.setLineWidth(1);
//			g.setColor(new Color(255, 150, 100));
//			g.fillRect(x, y-20, image.getWidth() * scale, 7);
//			
//			g.setColor(new Color(255, 255, 100));
//			g.fillRect(x, y-19, image.getWidth() * scale, 5);
			
			//g.fillOval(x-w/2, y-h/2, w*2, h*2);
			
//			SpriteSheet sheet2 = getOwner().getImageMissile();
//			Image image2 = sheet2.getSprite(0, team);
//			image2.rotate(theta);
//		
//			image2.draw(x,  y,  1f, new Color(0, 0, 255));
			
			//Images.missiles.getSubImage(x, y)
		}
		

		
	}
	

	
	public float getRestockProgress()
	{
		if(ability != null && ability instanceof LaunchMissile )
		{
			return ((LaunchMissile)ability).getRestockProgress();
		}
		else
		{
			return 0;
		}
	}
	
	public void ability(Point p)
	{
		
	}
	final protected void deathTrigger() { }


}
