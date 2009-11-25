package guri.screenmodel;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

public class UnitActionDetector implements Unit {

	private Unit unit = null;

	private boolean bubble = true;

	private Object obj = null;

	private LinkedList<Listener> listenerList = new LinkedList<Listener>();

	public UnitActionDetector() {
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public void setBubble(boolean bubble) {
		this.bubble = bubble;
	}

	public void setObject(Object object) {
		this.obj = object;
	}

	public boolean getBubble() {
		return bubble;
	}

	public void addListener(Listener listener) {
		synchronized (listenerList) {
			listenerList.add(listener);
		}
	}

	public void removeListener(Listener listener) {
		synchronized (listenerList) {
			listenerList.remove();
		}
	}

	private void notifyListener(MouseEvent me) {
		synchronized (listenerList) {
			Iterator<Listener> itr = listenerList.iterator();
			while (itr.hasNext()) {
				Listener listener = itr.next();
				listener.mouseEvent(this, me, obj);
			}
		}
	}

	public void paint(Graphics2D g) {
		if (unit == null)
			return;
		unit.paint(g);
	}

	public boolean mouseEvent(MouseEvent me) {
		if (unit == null)
			return false;
		boolean ret = unit.mouseEvent(me);
		if (ret)
			notifyListener(me);
		return (ret && bubble);
	}

	static public interface Listener {
		public void mouseEvent(UnitActionDetector uad, MouseEvent me, Object obj);
	}

}
