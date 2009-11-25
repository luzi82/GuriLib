/*
 * Created on May 13, 2005
 *
 */
package guri.util;

/**
 * @author cs_ltkaa
 * 
 */
public final class Syn {
    
    public void w(){
        wait(this);
    }
    
    public void u(){
        unWait(this);
    }
    
	public static void unWait(Object obj){
		synchronized (obj) {
			obj.notifyAll();
		}
	}

	public static void wait(Object obj) {
		try {
			synchronized (obj) {
				obj.wait();
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();
			System.exit(-1);
		}
	}

}
