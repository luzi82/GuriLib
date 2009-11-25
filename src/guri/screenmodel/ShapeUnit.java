package guri.screenmodel;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;

//import java.awt

public class ShapeUnit implements Unit {
	public final Shape shape;

	public final int mode;

	public final static int STROKE = 1;

	public final static int FILL = 2;

	public ShapeUnit(final Shape shape) {
		this.shape = shape;
		mode = STROKE;
	}

	public ShapeUnit(final Shape shape, int mode) {
		this.shape = shape;
		this.mode = mode;
	}

	public void paint(Graphics2D g) {
		if ((mode & STROKE) != 0)
			g.draw(shape);
		if ((mode & FILL) != 0)
			g.fill(shape);
	}

	public boolean mouseEvent(MouseEvent me) {
//		System.out.println(me.getPoint());
		return shape.contains(me.getPoint());
	}
}