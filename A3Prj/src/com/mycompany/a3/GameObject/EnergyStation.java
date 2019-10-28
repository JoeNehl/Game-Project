package com.mycompany.a3.GameObject;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class EnergyStation extends Fixed{
	
	private int capacity;
	private static double x=0;
	private static double y=0;
	private static int size;

	public EnergyStation(int [] colors, int gWX, int gWY) {
		super(x, y, size, colors, gWX, gWY);//calls the constructor in fixed
		this.capacity = setCapacity();
	}
	
	public int setCapacity() {
		this.capacity=getSize()*2;//sets the capacity of the EnergyStation as double size
		return capacity;
	}
	
	public void fade() {
		int colorR = getColor()[0];
		int colorG = getColor()[1];
		int colorB = getColor()[2];
		if (this.capacity!=0) {
			colorR=colorR/3;
			colorG=colorG/3;
			colorB=colorB/3;
			int [] newColor = {colorR, colorG, colorB};
			this.setColor(newColor);
		}
	}
	
	public void zeroCapacity() {//set capacity to zero
		this.capacity=0;
	}
	
	public int getCapacity() {//getter for capacity
		return this.capacity;
	}
	
	public String toString() {
		String parentDesc = super.toString();//calls the toString function in the fixed class
		String myDesc = " capacity=" + capacity;
		return "Energy Station:" + parentDesc + myDesc;//returns the string of the parent value and the string value of this class
	}
	
	public void draw(Graphics g, Point origin) {
		int hsize= getSize()/2;
		int x= (int)getX()+(int)origin.getX()-hsize;
		int y= (int)getY()+(int)origin.getY()-hsize;
		g.setColor(this.getColorInt());
		g.fillArc(x, y, getSize(), getSize(), 0, 360);
		g.setColor(0);
		g.drawString(String.valueOf(getCapacity()), (x), (y));
	}
}
