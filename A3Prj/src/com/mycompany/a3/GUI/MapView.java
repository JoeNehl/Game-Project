package com.mycompany.a3.GUI;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.GameObject.GameObject;
import com.mycompany.a3.Iterator.GameObjectCollection;
import com.mycompany.a3.Iterator.IIterator;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	public MapView() {
	}
	
	public void update (Observable o, Object arg) {
		gw = (GameWorld)o;
		gw.map();
		redraw();
	}
	
	private void redraw() {
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		GameObjectCollection goc = gw.getCollection();
		IIterator iterator = goc.getIterator();
		Point origin = new Point(getX(), getY());
		while (iterator.hasNext()) {
			GameObject o = (GameObject)iterator.getNext();
			o.draw(g, origin);
		}
	}
}
