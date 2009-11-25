package guri.util;

/**
 * Provide a method to convent a double to a close integer fraction.
 * 
 * @author luzi82
 *  
 */
public class Double2Fraction {

    /**
     * Convent a double into a integer fraction.
     * <p>
     * For an output a/b. a+b <= max.
     * 
     * @param ori
     *            the value to be convented.
     * @param max
     *            the maximun sum of a, b.
     * @return a fraction which is close to the double.
     */
    public static int[] convent(double ori, int max) {
        boolean neg = ori < 0;
        if (neg)
            ori = -ori;
        int[] out = { 0, 1 };
        int[] test = { 0, 1 };
        double d;
        for (int i = 0; i < max - 1; i++) {
            d = test[0] - test[1] * ori;
            if (d > 0)
                test[1]++;
            else if (d == 0) {
                VectorClone.clone(out, test);
                break;
            } else
                test[0]++;
            d = Math.abs(out[1] * ori - out[0]) * test[1]
                    - Math.abs(test[1] * ori - test[0]) * out[1];
            if (d > 0)
                VectorClone.clone(out, test);
        }
        if (neg)
            out[0] = -out[0];
        return out;
    }
}
