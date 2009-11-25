/*
 * Created on 2005/5/29
 *
 */
package guri.util;

/**
 * @author luzi82
 *  
 */
public class VectorClone {

    static public int[] clone(int[] a) {
        if (a == null)
            return null;
        int[] out = new int[a.length];
        clone(out, a);
        return out;
    }

    static public void clone(int[] to, int[] from) {
        if (to == null && from == null)
            return;
        if (to == null || from == null || to.length != from.length)
            throw new IllegalArgumentException();
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
    }

}
