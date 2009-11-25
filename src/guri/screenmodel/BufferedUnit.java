/**
 * height of the buffered image in pixel
 */
/**
 * width of the buffered image in pixel
 */
/**
 * the height of the buffer
 */
/**
 * the width of the buffer
 */
package guri.screenmodel;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * The object of this class stored a buffered image for the given sprite, so the
 * sprite does not need to be re-drawn in each repaint. however, if the sprite
 * is changed frequently, it is not recommented to use buffering. When the
 * sprite is changed, fire update() to update the buffer, otherwise the old
 * buffed sprite will be drawn.
 */
public class BufferedUnit implements Unit {
	/**
	 * the widht of the buffering image in pixel
	 */
	public int width;

	/**
	 * the height of the buffering image in pixel
	 */
	public int height;

	/**
	 * the sprite to be drawn
	 */
	final public Unit sprite;

	/**
	 * if true, the buffer will be updated in the next paint(), then this value
	 * will be setted to be false. otherwise do nothing.
	 */
	public boolean update = true;

	/** use value in BufferedImage */
	public int imageType = BufferedImage.TYPE_INT_ARGB;

	// private VolatileImage vi=null;
	/**
	 * the buffered image to be used for buffering
	 */
	private BufferedImage bi = null;

	/**
	 * create an intance by an sprite to be buffered and the size of buffer.
	 * 
	 * @param sprite
	 *            the sprite to be buffered
	 * @param w
	 *            the width of the buffered image in pixel
	 * @param h
	 *            the height of the buffered image in pixel
	 */
	public BufferedUnit(Unit sprite, int w, int h) {
		this.sprite = sprite;
		this.width = w;
		this.height = h;
	}

	/**
	 * update the content of buffer and set update to be false.
	 */
	public void update() {
		if (bi != null)
			bi.flush();
		if (width < 1)
			width = 1;
		if (height < 1)
			height = 1;
		bi = new BufferedImage(width, height, imageType);
		Graphics2D g2d = bi.createGraphics();
		g2d.clip(new Rectangle2D.Float(0, 0, width, height));
		sprite.paint(g2d);
		g2d.dispose();
		update = false;
	}

	/**
	 * draw the sprite buffer the sprite buffer will be updated when update ==
	 * true;
	 * 
	 * @param g
	 *            The Graphics object used for drawing
	 */
	public void paint(Graphics2D g) {
		if (update)
			update();
		g.drawImage(bi, null, 0, 0);
	}

	public boolean mouseEvent(MouseEvent me) {
		return sprite.mouseEvent(me);
	}
}
