package scenario;


import java.lang.reflect.Constructor;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

import core.Game;
import core.Utility;
import core.Values;
import objects.ambient.Asteroid;
import objects.ambient.BigStar;
import objects.ambient.Hazard;
import objects.ambient.HighYieldAsteroid;
import objects.ambient.Moon;
import objects.ambient.Nebula;
import objects.ambient.Pulsar;
import objects.base.Player;
import objects.units.BaseShip;
import objects.units.Unit;
import scenario.events.Event;
import scenario.events.None;
import scenario.events.Pulse;
import scenario.events.SolarFlare;
import teams.neutral.collectors.Collector;
import teams.neutral.pirate.*;


public class Scenario 
{
	private static String title;

	private Game game;

	private Player neutral;
	private BaseShip neutralBaseShip;
	private boolean hasNeutralBaseShip;

	private static ArrayList<Asteroid> asteroids;
	private static ArrayList<Hazard> hazards;
	private static ArrayList<Nebula> nebulae;

	private static int numAsteroids;
	private static int numHighYieldAsteroids;
	private static int numNebulae;
	private static int neutralSpawnCooldown;
	
	private static int asteroidSpawnWidth;
	private static int asteroidSpawnHeight;
	
	private static int asteroidHighYieldSpawnWidth;
	private static int asteroidHighYieldSpawnHeight;
	
	private static ArrayList<Class<? extends Unit>> unitPack;

	private static Adjective adjective;
	private static Noun noun;
	private static Battle battle;

	private static Event event;

	public Scenario(Game game) throws SlickException {

		this.game = game;
		game.setNeutral(null);
		asteroids = new ArrayList<Asteroid>();
		nebulae = new ArrayList<Nebula>();
		hazards = new ArrayList<Hazard>();

		hasNeutralBaseShip = false;
		neutralBaseShip = null;
		neutral = null;
		
		numAsteroids = 20;
		numHighYieldAsteroids = 5;
		numNebulae = 0;
		neutralSpawnCooldown = 10000;

		asteroidSpawnWidth = Values.ASTEROID_SPAWN_WIDTH;
		asteroidSpawnHeight = Values.ASTEROID_SPAWN_HEIGHT;
		
		asteroidHighYieldSpawnWidth = Values.ASTEROID_HIGH_YIELD_SPAWN_WIDTH;
		asteroidHighYieldSpawnHeight = Values.ASTEROID_HIGH_YIELD_SPAWN_HEIGHT;
		
		event = new None();
		unitPack = new ArrayList<Class<? extends Unit>>();
		
		battle = Battle.getRandom();

		selectNoun();
		applyAdjective();


	}



	public void manageEvents() {
		if (hasEvent()) {
			event.update();
		}

	}



	// The NOUN determines the major set piece that defines the environment
	public void selectNoun() throws SlickException
	{
		if(Game.basicMode)
		{
			noun = Noun.getBasic();
		}
		else
		{
			noun = Noun.getRandom();
		}
		
		
		switch (noun) {
		
		case VOID:
			adjective = Adjective.getBasicAdjective();
			numAsteroids -= 5;
			break;


		case NEBULA:
			adjective = Adjective.getNebulaAdjective();
			numNebulae += 3;
			break;

		case ASTEROID_BELT:
			adjective = Adjective.getAsteroidBeltAdjective();
			
			asteroidSpawnWidth = 5500;
			asteroidSpawnHeight = Values.PLAYFIELD_HEIGHT;

			asteroidHighYieldSpawnWidth = 5500;
			asteroidHighYieldSpawnHeight = Values.PLAYFIELD_HEIGHT;
			
			if (adjective.equals(Adjective.LEGENDARY)) {
				numHighYieldAsteroids *= 1.5 + numAsteroids;
				numAsteroids = 0;
			} else {
				numAsteroids *= 1.5;
				numHighYieldAsteroids *= 1.5;
			}
			break;

		case CLUSTER:
			adjective = Adjective.getAsteroidAdjective();
			asteroidSpawnWidth *= .67;
			asteroidSpawnHeight *= .67;
			asteroidHighYieldSpawnWidth *= .67;
			asteroidHighYieldSpawnHeight *= .67;
			break;
			
		
		case PULSAR:
			adjective = Adjective.getHazardAdjective();
			break;
			
		case STAR:
			adjective = Adjective.getHazardAdjective();
				break;
			
		case PIRATES:
			adjective = Adjective.getPirateAdjective();
			hasNeutralBaseShip = true;
			neutral = new Pirate(Values.NEUTRAL_ID, game);
			setupFaction(neutral);
			break;
			
		case COLLECTORS:

			adjective = Adjective.getCollectorsAdjective();
			hasNeutralBaseShip = true;
			neutral = new Collector(Values.NEUTRAL_ID, game);
			setupFaction(neutral);
			break;
			
		default:
			adjective = Adjective.getBasicAdjective();
			break;

		}

	}
	

	public void setupFaction(Player neutral) throws SlickException
	{
		game.setNeutral(neutral);
		neutralBaseShip = new BaseShip(neutral);
		game.addUnit(neutralBaseShip);
		neutral.setStartingMinerals();		
	}
	
	public void applyAdjective() {

		switch (adjective) {
		
		// No Effects
			
		case AVERAGE:
			break;
			
		case NORMAL:
			break;
			
		// Asteroid Effects (Number/Type)	
			
		case ABUNDANT:
			numAsteroids += 15;
			break;
			
		case BARREN:
			numHighYieldAsteroids -= 5;
			break;

		case POOR:
			numAsteroids += 10;
			numHighYieldAsteroids -= 5;
			break;
			
		case RICH:
			numAsteroids -= 10;
			numHighYieldAsteroids += 5;
			break;

		case SPARSE:
			numAsteroids -= 5;
			break;
	
		// Asteroid Effects (Location)	
		
		case DENSE:			
			asteroidSpawnWidth *= .67;
			asteroidSpawnHeight *= .67;
			asteroidHighYieldSpawnWidth *= .67;
			asteroidHighYieldSpawnHeight *= .67;
			numAsteroids += 5;
			break;
			
		// Nebula Effects
			
		case HIDDEN:
			numNebulae += 1;
			break;
			
		case SHROUDED:
			numNebulae += 2;
			break;

		// Hazard Effects	
			
		case LEGENDARY:
			break;
	
		case UNSTABLE:
			break;
		
		// Faction Effects
			
			
		case READY:	
			neutral.addMinerals(30);
				break;
				
		case RESPECTFUL:		
			if(neutral instanceof Pirate)
			{
				((Pirate) neutral).beRespectful();
			}
				break;
				
		case RELENTLESS:		
			neutral.setDifficultyRating(1.5f);
			break;

			
		default:
			break;


		}

	}
	
	public void addToUnitPack(Class<? extends Unit> clazz, int number) {
		for (int i = 0; i < number; i++) {
			unitPack.add(clazz);
		}
	}

	public static void clear() {
		asteroids.clear();
		nebulae.clear();
		hazards.clear();

		battle = null;
		adjective = null;
		noun = null;
	}

	public static ArrayList<Asteroid> getAsteroids() {
		ArrayList<Asteroid> temp = new ArrayList<Asteroid>();

		for (Asteroid a : asteroids) {
			temp.add(a);
		}
		return temp;
	}

	public static ArrayList<Nebula> getNebulae() {
		ArrayList<Nebula> temp = new ArrayList<Nebula>();

		for (Nebula a : nebulae) {
			temp.add(a);
		}
		return temp;
	}

	public static ArrayList<Hazard> getHazards() {
		ArrayList<Hazard> temp = new ArrayList<Hazard>();

		for (Hazard a : hazards) {
			temp.add(a);
		}
		return temp;
	}

	public Point getEdgeSpawn() {
		int r = Utility.random(4);
		final int OFFSCREEN = 200;

		switch (r) {
		// top
		case 0:
			return new Point(Utility.random(-Values.PLAYFIELD_WIDTH / 2, Values.PLAYFIELD_WIDTH / 2),
					-Values.PLAYFIELD_HEIGHT / 2 - OFFSCREEN);

		// bottom
		case 1:
			return new Point(Utility.random(-Values.PLAYFIELD_WIDTH / 2, Values.PLAYFIELD_WIDTH / 2),
					Values.PLAYFIELD_HEIGHT / 2 + OFFSCREEN);

		// left
		case 2:
			return new Point(-Values.PLAYFIELD_WIDTH / 2 - OFFSCREEN,
					Utility.random(-Values.PLAYFIELD_HEIGHT / 2, Values.PLAYFIELD_HEIGHT / 2));

		// right
		case 3:
			return new Point(Values.PLAYFIELD_WIDTH / 2 + OFFSCREEN,
					Utility.random(-Values.PLAYFIELD_HEIGHT / 2, Values.PLAYFIELD_HEIGHT / 2));
		}

		return new Point(0, 0);
	}

	public void update() throws SlickException 
	{

		if (Game.getTime() % neutralSpawnCooldown == 0 && Game.getTime() > neutralSpawnCooldown) {
			final int VARIANCE = 150;
			Point p = getEdgeSpawn();

			for (int i = 0; i < unitPack.size(); i++) {

				try {
					Class<? extends Unit> type = unitPack.get(i);
					Constructor<? extends Unit> c = type.getDeclaredConstructor(Player.class, int.class, int.class);

					int xSpawn = (int) (p.getX() + Utility.random(-VARIANCE, VARIANCE));
					int ySpawn = (int) (p.getY() + Utility.random(-VARIANCE, VARIANCE));

					Unit u1 = c.newInstance(neutral, xSpawn, ySpawn);
					Unit u2;

					if (hasSetPiece()) {
						u2 = c.newInstance(neutral, -xSpawn, ySpawn);
					} else {
						u2 = c.newInstance(neutral, -xSpawn, -ySpawn);
					}

					u1.setHighlight(250);
					u2.setHighlight(250);

					game.addUnit(u1);
					game.addUnit(u2);
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}

	public void spawn() throws SlickException 
	{
		title = battle + " the " + adjective + " " + noun;

		// Spawn Asteroids
		spawnAsteroidRandom(numAsteroids);
		spawnAsteroidRandomHighYield(numHighYieldAsteroids);

		// Spawn Nebulae
		spawnNebulaRandom(numNebulae);

		// Spawn Big Star
		if (hasStar()) {
			spawnBigStar();
		}

		// Spawn Pulsar
		if (hasPulsar()) {
			spawnPulsar();
		}
		
		if(hasMoon())
		{
			spawnMoon();
		}

	}

	/********** ACCESSOR METHODS ***********/

	public boolean hasSetPiece() {
		return hasNeutralBaseShip() || hasEvent();
	}

	public Player getNeutral() {
		return neutral;
	}

	public BaseShip getNeutralBaseShip() {
		return neutralBaseShip;
	}

	public boolean hasNeutralBaseShip() {
		return hasNeutralBaseShip;
	}

	public static String getTitle() {
		return title;
	}

	public static int getEventCountdown() {
		return event.getCountdown();
	}

	public static boolean hasEvent() {
		return !(event instanceof None);
	}

	public static int getNumAsteroids() {
		return numAsteroids;
	}

	public static int getNumHighYieldAsteroids() {
		return numHighYieldAsteroids;
	}

	public static int getNumNebulae() {
		return numNebulae;
	}

	public static boolean isLegendary() {
		return adjective.equals(Adjective.LEGENDARY);
	}
	
	public static boolean isUnstable() {
		return adjective.equals(Adjective.UNSTABLE);
	}
	
	public static boolean hasMoon()
	{
		return noun == Noun.MOON;
	}
	public static boolean hasStar() {
		return noun == Noun.STAR;
	}

	public static boolean hasPulsar() {
		return noun == Noun.PULSAR;
	}

	public static Adjective getAdjective() {
		return adjective;
	}
	
	public static Noun getNoun()
	{
		return noun;
	}

	/********** SPAWNING METHODS ***********/

	

	public void spawnNebulaRandom(int amount) throws SlickException {
		for (int i = 0; i < amount; i++) {
			int x = Utility.random(-Values.NEBULA_SPAWN_WIDTH, Values.NEBULA_SPAWN_HEIGHT);
			int y = Utility.random(-Values.NEBULA_SPAWN_WIDTH, Values.NEBULA_SPAWN_HEIGHT);
			int size = Utility.random(Values.NEBULA_MIN_SIZE, Values.NEBULA_MAX_SIZE);

			nebulae.add(new Nebula(x, y, size));

			if (hasSetPiece()) {
				nebulae.add(new Nebula(-x, y, size));
			} else {
				nebulae.add(new Nebula(-x, -y, size));
			}
		}

	}

	public void spawnBigStar() throws SlickException {
		BigStar s;
		if (isLegendary()) {
			s = new BigStar(0, Values.HAZARD_Y_POSITION, Values.STAR_LEGENDARY_SIZE);
		} else {
			s = new BigStar(0, Values.HAZARD_Y_POSITION);

		}

		hazards.add(s);
		event = new SolarFlare(neutral, isUnstable(), isLegendary());
		event.linkHazard(s);
	}

	public void spawnPulsar() throws SlickException {
		Pulsar p;
		if (isLegendary()) {
			p = new Pulsar(0, Values.HAZARD_Y_POSITION, Values.PULSAR_LEGENDARY_SIZE);
		} else {
			p = new Pulsar(0, Values.HAZARD_Y_POSITION);
		}

		hazards.add(p);
		event = new Pulse(neutral, isUnstable(), isLegendary());
		event.linkHazard(p);
	}
	
	public void spawnMoon() throws SlickException {
		Moon m;
		if (isLegendary()) {			
			m = new Moon(0,  0, Values.ASTEROID_MAX_SIZE*6);
		} else {
			m = new Moon(0,  0, Values.ASTEROID_MAX_SIZE*4);
		}

		asteroids.add(0, m);
	}

	public void spawnAsteroidRandom(int amount) throws SlickException {
		for (int i = 0; i < amount; i++) 
		{
			int rX = Utility.random(-asteroidSpawnWidth / 2, asteroidSpawnWidth / 2);
			int rY = Utility.random(-asteroidSpawnHeight / 2, asteroidSpawnHeight / 2);

			if (Asteroid.isValidSpawn(rX, rY))
			{
				spawnAsteroid(rX, rY, randomAsteroidSize());
			}
			else
			{
				i--;
			}

		}
	}

	public void spawnAsteroidRandomHighYield(int amount) throws SlickException {
		for (int i = 0; i < amount; i++) {
			int rX = Utility.random(-asteroidHighYieldSpawnWidth / 2, asteroidHighYieldSpawnWidth / 2);
			int rY = Utility.random(-asteroidHighYieldSpawnHeight / 2, asteroidHighYieldSpawnHeight / 2);

			if (Asteroid.isValidSpawn(rX, rY))
			{
				spawnHighYieldAsteroid(rX, rY, randomAsteroidSize());
			}
			else
			{
				i--;
			}

		}
	}
	
	public void spawnAsteroid(int x, int y, int size) throws SlickException {
		float xSpeed = Utility.random(Values.ASTEROID_MIN_SPEED, Values.ASTEROID_MAX_SPEED);
		float ySpeed = Utility.random(Values.ASTEROID_MIN_SPEED, Values.ASTEROID_MAX_SPEED);

		if (Math.random() > .5)
			xSpeed *= -1;
		if (Math.random() > .5)
			ySpeed *= -1;

		Asteroid a = new Asteroid(x, y, xSpeed, ySpeed, size);
		Asteroid b;

		if (hasSetPiece()) {
			b = new Asteroid(-x, y, -xSpeed, ySpeed, size);
		} else {
			b = new Asteroid(-x, -y, -xSpeed, -ySpeed, size);
		}

		a.invertCorner();
		b.invertCorner();

		asteroids.add(a);
		asteroids.add(b);

	}

	public void spawnHighYieldAsteroid(int x, int y, int size) throws SlickException {

		float xSpeed = Utility.random(Values.ASTEROID_MIN_SPEED, Values.ASTEROID_MAX_SPEED);
		float ySpeed = Utility.random(Values.ASTEROID_MIN_SPEED, Values.ASTEROID_MAX_SPEED);

		if (Math.random() > .5)
			xSpeed *= -1;
		if (Math.random() > .5)
			ySpeed *= -1;

		Asteroid a = new HighYieldAsteroid(x, y, xSpeed, ySpeed, size);
		Asteroid b;

		if (hasSetPiece()) {
			b = new HighYieldAsteroid(-x, y, -xSpeed, ySpeed, size);
		} else {
			b = new HighYieldAsteroid(-x, -y, -xSpeed, -ySpeed, size);
		}

		a.invertCorner();
		b.invertCorner();

		asteroids.add(a);
		asteroids.add(b);

	}

	public int randomAsteroidSize() {
		double r = Math.random();

		if (r < .25) {
			return Values.ASTEROID_MIN_SIZE;
		} else if (r > .75) {
			return Values.ASTEROID_MAX_SIZE;
		} else {
			return Values.ASTEROID_MID_SIZE;
		}

	}

}
