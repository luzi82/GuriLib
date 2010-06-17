package guri.util;

public class Pair<T extends Comparable<T>, U extends Comparable<U>> implements
		Comparable<Pair<T, U>> {

	public T iFirst;
	public U iSecond;

	@Override
	public int compareTo(Pair<T, U> arg0) {
		int ret = iFirst.compareTo(arg0.iFirst);
		if (ret != 0)
			return ret;
		ret = iSecond.compareTo(arg0.iSecond);
		if (ret != 0)
			return ret;
		return 0;
	}

}
