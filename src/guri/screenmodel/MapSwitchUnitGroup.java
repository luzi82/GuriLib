/*
 * MapSwitchUnitGroup.java
 *
 * Created on 2004�~9��24��, �U�� 11:02
 */

package guri.screenmodel;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Map;

/**
 * Provide a sprite group which can switch displaying different sprites using a
 * key. If a key is toggled by setToggle, the sprites mapped by the key will be
 * painted when paint() is called. A null key can be used as key of default
 * value.
 * 
 * @author luzi82
 */
public class MapSwitchUnitGroup implements Unit {

	private final Map<Object, Unit> map = new Hashtable<Object, Unit>();

	private Unit def = null;

	private Object key = null;

	/** Creates a new instance of MapSwitchUnitGroup */
	public MapSwitchUnitGroup() {
	}

	/**
	 * add a new sprite with a key
	 * 
	 * @param k
	 *            the key
	 * @param m
	 *            the sprite to be added
	 * @return the old sprite for that key, null if no that sprite.
	 */
	public Unit put(Object k, Unit m) {
		synchronized (map) {
			if (k == null) {
				Unit oldDef = def;
				def = m;
				return oldDef;
			} else {
				return map.put(k, m);
			}
		}
	}

	/**
	 * remove a sprite by a key
	 * 
	 * @param k
	 *            the key to be deleted
	 * @return the sprite removed
	 */
	public Unit remove(Object k) {
		synchronized (map) {
			if (k == null) {
				Unit oldDef = def;
				def = null;
				return oldDef;
			} else {
				return (Unit) map.remove(k);
			}
		}
	}

	/**
	 * switch the sprite by a key
	 * 
	 * @param k
	 *            the key
	 * @return return the old key
	 */
	public Object setToggle(Object k) {
		Object out = getToggle();
		key = k;
		return out;
	}

	/**
	 * get the key which is toggled
	 * 
	 * @return the key now in active
	 */
	public Object getToggle() {
		return key;
	}

	/** clear the map and key */
	public void clear() {
		synchronized (map) {
			map.clear();
			def = null;
		}
		key = null;
	}

	/**
	 * get the sprite mapped by the current key
	 * 
	 * @return the sprite mapped by the current key
	 */
	public Unit getUnit() {
		return getUnit(getToggle());
	}

	/**
	 * get the Unit mapped by the given key
	 * 
	 * @param k
	 *            a key to map a sprite
	 * @return the sprite mapped by the key, return null if the key does not
	 *         exist.
	 */
	public Unit getUnit(Object k) {
		synchronized (map) {
			return (Unit) map.get(k);
		}
	}

	/**
	 * paint the sprite which mapped by the current key.
	 * 
	 * @param g
	 *            A Graphics object used.
	 */
	public void paint(Graphics2D g) {
		Unit e = null;
		if (getToggle() == null) {
			if (def != null) {
				def.paint(g);
			}
			return;
		}
		e = getUnit();
		if (e == null)
			return;
		e.paint(g);
	}

	public boolean mouseEvent(MouseEvent me) {
		Unit e = null;
		if (getToggle() == null) {
			if (def != null) {
				return def.mouseEvent(me);
			}
			return false;
		}
		e = getUnit();
		if (e == null)
			return false;
		return e.mouseEvent(me);
	}

}