/*
 * Created on 2005/5/29
 *
 */
package guri.util.test;

import guri.util.Double2Fraction;

/**
 * @author luzi82
 *  
 */
public class Double2FractionTest {

    public static void main(String[] args) {
        for (int i = 0; i <= 90; i++) {
            double sin = Math.sin(i * Math.PI / 180);
            int[] f = Double2Fraction.convent(sin, 10000);
            System.out.println("sin(" + i + ") = " + sin + " = " + f[0] + " / "
                    + f[1]);
        }
    }
}
