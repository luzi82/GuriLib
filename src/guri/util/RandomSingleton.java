package guri.util;

import java.util.Random;

public class RandomSingleton {

	private Random random;

	private RandomSingleton() {
		random = new Random();
	}

	public Random random() {
		return random;
	}

	// ///////////////////////////
	// Singleton
	// //////////////////////////

	private static RandomSingleton instance = null;

	public static RandomSingleton instance() {
		if (instance == null)
			instance = new RandomSingleton();
		return instance;
	}

}
