package guri.screenmodel;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.TreeMap;

public class ViewGroup<XView extends View> implements Unit {

	private Map<Integer, XView> viewMap = new TreeMap<Integer, XView>();

	private XView currentView=null;

	public void addView(XView view) {
		viewMap.put(view.id(), view);
	}

	public void switchView(int id) {
		if (currentView != null)
			currentView.disable();
		currentView = viewMap.get(id);
		if (currentView != null)
			currentView.enable();
	}

	public XView currentView() {
		return currentView;
	}

	public void paint(Graphics2D g) {
		if (currentView != null)
			currentView.paint(g);
	}

	public boolean mouseEvent(MouseEvent me) {
		if (currentView != null)
			return currentView.mouseEvent(me);
		return false;
	}

}
