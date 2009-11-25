package guri.screenmodel;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class MouseEventFilterUnit implements Unit {

	protected Unit unit = null;

	protected abstract boolean filter(MouseEvent me);

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public void paint(Graphics2D g) {
		if (unit == null)
			return;
		unit.paint(g);
	}

	public boolean mouseEvent(MouseEvent me) {
		if (unit == null)
			return false;
		if (!filter(me))
			return false;
		return unit.mouseEvent(me);
	}

}
