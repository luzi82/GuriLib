package guri.screenmodel;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

public class TextUnit implements Unit {
	public Font font;

	public String text;

	public int axis;

	private float x0, x1, y0, y1;

	public TextUnit(Font font, String text) {
		this(font, text, 0);
	}

	public TextUnit(Font font, String text, int axis) {
		if (font == null)
			throw new NullPointerException();
		this.font = font;
		this.text = text;
		this.axis = axis;
	}

	public void paint(Graphics2D g2d) {
		if (text == null || text.equals("")) {
			x0 = y0 = 1;
			x1 = y1 = -1;
			return;
		}
		float x, y;
		float textWidth;
		float textAscent;
		float textDescent;
		FontRenderContext frc = g2d.getFontRenderContext();
		TextLayout tl = new TextLayout(text, font, frc);
		textAscent = tl.getAscent();
		textDescent = tl.getDescent();
		textWidth = tl.getVisibleAdvance();
		int tempAxis;
		tempAxis = axis % 3;
		x = (tempAxis == 0) ? 0 : (tempAxis == 1) ? -textWidth / 2 : -textWidth;
		tempAxis = axis / 3;
		y = (tempAxis == 0) ? textAscent
				: (axis / 3 == 1) ? (textAscent - textDescent) / 2
						: -textDescent;
		g2d.setFont(font);
		g2d.drawString(text, x, y);
		x0 = x;
		y0 = y + textDescent - textAscent;
		x1 = x + textWidth;
		y1 = y;
	}

	public boolean mouseEvent(MouseEvent me) {
		if (me.getX() < x0)
			return false;
		if (me.getX() > x1)
			return false;
		if (me.getY() < y0)
			return false;
		if (me.getY() > y1)
			return false;
		return true;
	}

	public int getWidth(Component c) {
		if (text == null || text.equals(""))
			return 0;
		FontMetrics fm = c.getFontMetrics(font);
		return fm.stringWidth(text);
	}

	public int getAscent(Component c) {
		FontMetrics fm = c.getFontMetrics(font);
		return fm.getAscent();
	}

	public int getDescent(Component c) {
		FontMetrics fm = c.getFontMetrics(font);
		return fm.getDescent();
	}

	public int getLeading(Component c) {
		FontMetrics fm = c.getFontMetrics(font);
		return fm.getLeading();
	}

	public int getHeight(Component c) {
		FontMetrics fm = c.getFontMetrics(font);
		return fm.getHeight();
	}

}