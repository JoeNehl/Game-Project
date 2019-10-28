package com.mycompany.a3.GameObject;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class Base extends Fixed {
	private int sequenceNumber;
	
	public Base(double x, double y, int size, int number, int [] colors, int gWX, int gWY) {
		super(x, y, size, colors, gWX, gWY);
		this.sequenceNumber=number;//number is the value assigned in GameWorld, increments for each base created
	}
	
	public int checkCollision() {//used in checking if base number is the next one the robot needs to travel to
		return this.sequenceNumber;
	}
	
	public String toString() {
		String parentDesc = super.toString();//calls the toString function in the fixed class
		String myDesc = " sequenceNumber=" + this.sequenceNumber;
		return "Base:" + parentDesc + myDesc;//returns the string of the parent value and the string value of this class
	}
	
	public void draw(Graphics g, Point origin) {
		int hsize= getSize()/2;
		int [] xs = { (int)getX()+(int)origin.getX(), (int)getX()+(int)origin.getX()+getSize(), (int)getX()+(int)origin.getX()+hsize };
		int [] ys = { (int)getY()+(int)origin.getY(), (int)getY()+(int)origin.getY(), (int)getY()+(int)origin.getY()+getSize()};
		g.setColor(this.getColorInt());
		g.fillPolygon(xs, ys, 3);
		g.setColor(0);
		g.drawString(String.valueOf(sequenceNumber), ((int)getX()+(int)origin.getX()), ((int)getY()+(int)origin.getY()));
	}

}
