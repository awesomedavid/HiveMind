package scenario;

import core.Utility;

public enum Adjective {
	NORMAL, AVERAGE,								// No effects
	SPARSE, POOR, BARREN, RICH, ABUNDANT,  			// Asteroid number effects
	DENSE,											// Asteroid location effects
	HIDDEN, SHROUDED, 								// Nebulae
	UNSTABLE, LEGENDARY, 							// Star / Pulsar
	READY, RESPECTFUL, RELENTLESS;					// Factions
	

	// Legendary --> Doubles the effect of the noun... 2x damage star, etc ?

	public static Adjective getRandom() {
		return Adjective.values()[(Utility.random(Adjective.values().length))];
	}
	
	public static Adjective getBasicAdjective()
	{
		Adjective[] adjective = {Adjective.NORMAL, Adjective.AVERAGE, Adjective.SPARSE, Adjective.POOR, Adjective.RICH, Adjective.ABUNDANT, Adjective.BARREN};
		return adjective[Utility.random(adjective.length)];
	}
	
	public static Adjective getAsteroidAdjective()
	{
		Adjective[] adjective = {Adjective.NORMAL, Adjective.AVERAGE, Adjective.SPARSE, Adjective.POOR, Adjective.RICH, Adjective.ABUNDANT, Adjective.BARREN, 
								 Adjective.DENSE};
		return adjective[Utility.random(adjective.length)];
	}
	
	public static Adjective getNebulaAdjective()
	{
		Adjective[] adjective = {Adjective.NORMAL, Adjective.AVERAGE, Adjective.POOR, Adjective.RICH, Adjective.ABUNDANT, Adjective.BARREN, 
								Adjective.HIDDEN, Adjective.SHROUDED};
		return adjective[Utility.random(adjective.length)];
	}
	
	public static Adjective getAsteroidBeltAdjective()
	{
		Adjective[] adjective = {Adjective.NORMAL, Adjective.AVERAGE, Adjective.SPARSE, Adjective.POOR, Adjective.RICH, Adjective.ABUNDANT, Adjective.BARREN, 
								 Adjective.LEGENDARY};
		return adjective[Utility.random(adjective.length)];
	}


	public static Adjective getHazardAdjective()
	{
		Adjective[] adjective = {Adjective.NORMAL, Adjective.AVERAGE, Adjective.LEGENDARY, Adjective.UNSTABLE, Adjective.HIDDEN, Adjective.SHROUDED};
		return adjective[Utility.random(adjective.length)];
	}

	
	public static Adjective getPirateAdjective()
	{
		Adjective[] adjective = {Adjective.NORMAL, Adjective.AVERAGE, Adjective.READY, Adjective.RELENTLESS, Adjective.RESPECTFUL, Adjective.HIDDEN, Adjective.SHROUDED};
		return adjective[Utility.random(adjective.length)];
	}
	
	
	public static Adjective getCollectorsAdjective()
	{
		Adjective[] adjective = {Adjective.NORMAL, Adjective.AVERAGE, Adjective.HIDDEN, Adjective.SHROUDED};
		return adjective[Utility.random(adjective.length)];
	}

	public String toString() {
		String word = super.toString().toLowerCase();
		String first = word.toUpperCase().substring(0, 1);
		word = first + word.substring(1, word.length());

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == '_') {
				word = word.substring(0, i) + " " + word.substring(i + 1, i + 2).toUpperCase()
						+ word.substring(i + 2, word.length());
			}

		}

		return word;
	}
}
	

