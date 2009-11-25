/*
 * Created on May 21, 2005
 *
 */
package guri.util;

/**
 * used to handle a Throwable.
 * 
 * @author luzi82
 *
 */
public interface ThrowableHandler {
    
    /**
     * Handle a Throwable throwing event.
     * @param t the Throwable which is thrown.
     */
    public void handle(Throwable t);
}
