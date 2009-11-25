package guri.screenmodel;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;

import javax.swing.event.MouseInputListener;

/**
 * A canvas contains a sprite
 */
public class UnitCanvas extends Canvas implements MouseInputListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5454851343825655812L;

	/**
	 * the sprite to be drawn to the canvas
	 */
	final protected Unit unit;

	/**
	 * the renderingHint for drawing
	 * 
	 * @see java.awt.RenderingHints
	 */
	public RenderingHints renderingHints = new RenderingHints(null);

	/**
	 * to state whether it is a need to clear the display before each paint
	 */
	public boolean clearRect = true;

	/**
	 * to state how many buffer to be used.
	 * 
	 * @see java.awt.Canvas
	 */
	public final int buffer;

	/**
	 * the toolkit of this Canvas
	 */
	private Toolkit toolkit = getToolkit();

	/**
	 * state whether buffer strategy is built
	 */
	private boolean bufferCreated = false;

	/**
	 * create an instance same as UnitCanvas(sprite,2)
	 * 
	 * @param sprite
	 *            the sprite to be put in this canvas
	 */
	public UnitCanvas(Unit sprite) {
		this(sprite, 2);
	}

	/**
	 * create an intance
	 * 
	 * @param sprite
	 *            the sprite to be drawn
	 * @param buffer
	 *            number of buffer
	 */
	public UnitCanvas(Unit sprite, int buffer) {
		super();
		int b = (buffer >= 1) ? buffer : 1;
		this.unit = sprite;
		this.buffer = b;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		// createBufferStrategy(b);
	}

	/**
	 * paint the canvas
	 * 
	 * @param g
	 *            the Graphics object for paint.
	 */
	public void paint(Graphics g) {
		// no know why, but the bufferstrategy should be set after construction
		if (!bufferCreated) {
			createBufferStrategy(buffer);
			bufferCreated = true;
		}

		int w = getWidth();
		int h = getHeight();
		BufferStrategy bufferStrategy = getBufferStrategy();
		Graphics2D gg = (Graphics2D) bufferStrategy.getDrawGraphics();
		gg.setRenderingHints(renderingHints);
		if (clearRect)
			gg.clearRect(0, 0, w, h);
		gg.clip(new Rectangle2D.Float(0, 0, w, h));
		unit.paint(gg);
		gg.dispose();
		bufferStrategy.show();
		toolkit.sync();

		g.dispose();
	}

	/**
	 * repaint the obj
	 * 
	 * @param g
	 *            the Graphic obj
	 */
	public void repaint(Graphics g) {
		paint(g);
	}

	/**
	 * update
	 * 
	 * @param g
	 *            the Graphic obj
	 */
	public void update(Graphics g) {
		paint(g);
	}

	public void mouseClicked(MouseEvent arg0) {
		unit.mouseEvent(arg0);
	}

	public void mousePressed(MouseEvent arg0) {
		unit.mouseEvent(arg0);
	}

	public void mouseReleased(MouseEvent arg0) {
		unit.mouseEvent(arg0);
	}

	public void mouseEntered(MouseEvent arg0) {
		unit.mouseEvent(arg0);
	}

	public void mouseExited(MouseEvent arg0) {
		unit.mouseEvent(arg0);
	}

	public void mouseDragged(MouseEvent arg0) {
		unit.mouseEvent(arg0);
	}

	public void mouseMoved(MouseEvent arg0) {
		unit.mouseEvent(arg0);
	}

}