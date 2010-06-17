package guri.util;

import java.util.Random;

public class RandomString {

	public static String gen(int len) {
		Random random = RandomSingleton.instance().random();
		final int MAX = 10 + 26 * 2;
		String out = "";
		for (int i = 0; i < len; ++i) {
			int t = random.nextInt(MAX);
			if (t < 10) {
				out += (char)('0' + t);
			} else if (t < 10 + 26) {
				out += (char)('A' + (t - 10));
			} else {
				out += (char)('a' + (t - 10 - 26));
			}
		}
		return out;
	}

}
