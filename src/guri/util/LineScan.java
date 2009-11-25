/*
 * Created on 2005/5/27
 *
 */
package guri.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author luzi82
 *  
 */
public class LineScan {

    final BufferedReader br;

    public LineScan(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }
    
    public String getNext() {
        try {
            return br.readLine();
        } catch (Throwable t) {
            return null;
        }
    }

}
