package com.mycompany.a3.GameObject;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public abstract class GameObject implements IDrawable, ICollider {
	private int size;
	private double x;
	private double y;
	private int[] colors = new int[3];
	private int gameSizeX;
	private int gameSizeY;
	private ArrayList <Object> coll;
	
	public GameObject(double x, double y, int size, int [] c, int gWX, int gWY) {
		this.gameSizeX=gWX;
		this.gameSizeY=gWY;
		if (x==0) {//if/else statement determining whether or not values should be random or assigned value
			setX();
		} else {
			this.x=x;
		}
		if (y==0) {
			setY();
		} else {
			this.y=y;
		}
		if (size==0) {
			setSize();
		}else {
			this.size=size;
		}
		this.setColor(c);
		coll = new ArrayList <Object>();
	}
	
	public void setColor(int [] colors) {
		this.colors=colors;
	}
	
	public int [] getColor() {
		return this.colors;
	}
	
	public void setX() {//random value less than 1024
		Random rand = new Random();
		this.x = rand.nextDouble() * (gameSizeX);
	}
	
	public void setX(double newX) {//sets a given value of x
		this.x=newX;
	}
	
	public void setY() {//random value of 768
		Random rand = new Random();
		this.y = rand.nextDouble() * (gameSizeY);
	}
	
	public void setY(double newY) {//sets a given value of y
		this.y=newY;
	}
	
	public void setSize() {//sets a given value of size
		Random rand = new Random();
		this.size = rand.nextInt(50)+25;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	public String toString() {
		String myDesc = (" location=(" + Math.round(getX()*10.0)/10.0 + "," + Math.round(getY()*10.0)/10.0 + ") color=["+ this.colors[0] + "," + this.colors[1] + "," + this.colors[2] + "] size=" + getSize());
		return myDesc;//returns the string value of this class
	}
	
	public int getColorInt() {
		return ColorUtil.rgb(this.colors[0], this.colors[1], this.colors[2]);
	}
	
	public int getGameSizeX() {
		return gameSizeX;
	}
	
	public int getGameSizeY() {
		return gameSizeY;
	}
	
	public void draw(Graphics g, Point origin) {
		
	}
	
	public boolean collidesWith(GameObject check) {
		int hsize = this.getSize()/2;
		int hsize2 = check.getSize()/2;
		int r1 = (int)getX()+hsize;
		int l1 = (int)getX()-hsize;
		int t1 = (int)getY()+hsize;
		int b1 = (int)getY()-hsize;
		int r2 = (int)check.getX()+hsize2;
		int l2 = (int)check.getX()-hsize2;
		int t2 = (int)check.getY()+hsize2;
		int b2 = (int)check.getY()-hsize2;
		if ((r1 < l2) || (l1 > r2) || (t2 < b1) || (t1 < b2)) {
			if (this.coll.contains(check)) {
				this.coll.remove(check);
			}
			return false;
			
		} else {
			if (this.coll.contains(check)) {
				return false;
			}else {
				this.coll.add(check);
				return true;
			}

		}
	}
	
	public void removeFromList(GameObject check) {
		if (this.coll.contains(check)) {
			this.coll.remove(check);
		}
	}
	
	public void addToList(GameObject check) {
		if (this.coll.contains(check)) {
		}else {
			this.coll.add(check);
		}
	}
	
	
	public void handleCollision(GameObject check) {
		
	}
}
