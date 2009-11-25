package guri.screenmodel;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;

public class ImageUnit implements Unit {
	public final Image image;

	public final ImageObserver obs;

	public ImageUnit(final Image image, final ImageObserver obs) {
		this.image = image;
		this.obs = obs;
	}

	public void paint(Graphics2D g) {
		g.drawImage(image, 0, 0, obs);
	}

	public boolean mouseEvent(MouseEvent me) {
		if (me.getX() < 0)
			return false;
		if (me.getX() > image.getWidth(obs))
			return false;
		if (me.getY() < 0)
			return false;
		if (me.getY() > image.getHeight(obs))
			return false;
		return true;
	}

}
