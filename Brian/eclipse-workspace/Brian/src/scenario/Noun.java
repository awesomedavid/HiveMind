package scenario;

import core.Utility;

public enum Noun 
{
	VOID, EXPANSE, ZONE, SECTOR, SYSTEM, FIELD, 
	ASTEROID_BELT, CLUSTER, 
	MOON,
	NEBULA,
	PULSAR, STAR,  
	PIRATES, COLLECTORS;

	public static Noun getRandom() {
		return Noun.values()[(Utility.random(Noun.values().length))];
	}
	
	public static Noun getBasic()
	{
		Noun[] nouns = {Noun.VOID, Noun.ZONE, Noun.SECTOR, Noun.EXPANSE, Noun.SYSTEM, Noun.FIELD};
		return nouns[Utility.random(nouns.length)];
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
