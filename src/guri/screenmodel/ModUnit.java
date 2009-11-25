package guri.screenmodel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class ModUnit implements Unit {
	public final Unit sprite;

	public AffineTransform affineTransform = new AffineTransform();

	public RenderingHints renderingHints = new RenderingHints(null);

	public Color color = null;

	public Stroke stroke = null;

	public boolean visible = true;

	public double alpha = 1.0;

	public Shape clip = null;

	public Area chop = null;

	public ModUnit(final Unit sprite) {
		this.sprite = sprite;
	}

	public void paint(Graphics2D g) {
		Graphics2D gg = (Graphics2D) g.create();
		if (!visible)
			return;
		if (alpha <= 0)
			return;
		if (color != null)
			gg.setColor(color);
		if (stroke != null)
			gg.setStroke(stroke);
		if (clip != null)
			gg.clip(clip);
		if (chop != null) {
			Area clip;
			clip = new Area(gg.getClip());
			clip.subtract(chop);
			gg.setClip(clip);
		}

		if (alpha < 1)
			gg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					(float) alpha));
		gg.transform(affineTransform);
		gg.addRenderingHints(renderingHints);
		sprite.paint(gg);
		gg.dispose();
		gg = null;
	}

	public boolean mouseEvent(MouseEvent me) {
		try {
			Point2D point = me.getPoint();
			if (clip != null && !clip.contains(point))
				return false;
			if (chop != null && chop.contains(point))
				return false;
			Point2D p2 = affineTransform.inverseTransform(point, null);
			MouseEvent me2 = new MouseEvent(me.getComponent(), me.getID(), me
					.getWhen(), me.getModifiers(), (int) Math.round(p2.getX()),
					(int) Math.round(p2.getY()), me.getClickCount(), me
							.isPopupTrigger(), me.getButton());
			return sprite.mouseEvent(me2);
		} catch (NoninvertibleTransformException nte) {
			return false;
		}
	}
}
