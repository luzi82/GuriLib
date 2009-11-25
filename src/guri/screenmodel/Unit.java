package guri.screenmodel;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * A object displayed on a screen.
 */
public interface Unit {

	public enum Action {
		CLICKED, ENTERED, EXITED, PRESSED, RELEASED, DRAGGED, MOVED
	}

	/**
	 * Draw sprite
	 * 
	 * @param g
	 *            the Graphic object to be used
	 */
	void paint(Graphics2D g);

	boolean mouseEvent(MouseEvent me);
}