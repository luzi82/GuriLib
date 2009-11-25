package guri.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class RandomOrder {

	public static int[] randomOrder(int size) {
		int i;
		Random random = RandomSingleton.instance().random();
		int[][] node = new int[size][2];
		for (i = 0; i < size; ++i) {
			node[i][0] = i;
			node[i][1] = random.nextInt();
		}
		Arrays.sort(node, new IntArrayComparator());
		int[] ret = new int[size];
		for (i = 0; i < size; ++i) {
			ret[i] = node[i][0];
		}
		return ret;
	}

	public static class IntArrayComparator implements Comparator<int[]> {

		public int compare(int[] o1, int[] o2) {
			if (o1.length != 2)
				throw new IllegalArgumentException();
			if (o2.length != 2)
				throw new IllegalArgumentException();
			Integer o11 = o1[1];
			Integer o21 = o2[1];
			int ret;
			ret = o11.compareTo(o21);
			if (ret != 0)
				return ret;
			Integer o10 = o1[0];
			Integer o20 = o2[0];
			return o10.compareTo(o20);
		}

	}
}
