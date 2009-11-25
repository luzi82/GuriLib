/*
 * Created on May 13, 2005
 *
 */
package guri.util;

/**
 * @author cs_ltkaa
 * 
 */
public class Compare {

	public static int compare(byte[][] a, byte[][] b) {
		if (a == null && b == null)
			return 0;
		if (a == null)
			return -1;
		if (b == null)
			return 1;
		int diff = a.length - b.length;
		if (diff != 0)
			return diff;
		for (int i = 0; i < a.length; i++) {
			diff = compare(a[i], b[i]);
			if (diff != 0)
				return diff;
		}
		return 0;
	}

	public static int compare(byte[] a, byte[] b) {
		if (a == null && b == null)
			return 0;
		if (a == null)
			return -1;
		if (b == null)
			return 1;
		int diff = a.length - b.length;
		if (diff != 0)
			return diff;
		for (int i = 0; i < a.length; i++) {
			diff = a[i] - b[i];
			if (diff != 0)
				return diff;
		}
		return 0;
	}

	public static int compare(int a, int b) {
		return a - b;
	}

	private Compare() {
	}

}
