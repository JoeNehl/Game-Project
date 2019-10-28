package com.mycompany.a3.GameObject;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class Drone extends Movable {
	
	private static double x;
	private static double y;
	private static int speed=1;//initializes speed to 1, constructor class in movable checks if value is not 0. if not 0 randomizes value
	private static int heading=1;//initializes heading to 1, constructor class in movable checks if value is not 0. if not 0 randomizes value
	private static int size;

	public Drone(int [] colors, int gWX, int gWY) {
		super(x, y, size, colors, heading, speed, gWX, gWY);//calls constructor in movable
	}
	
	public void shift() {//this function decides if the drone stays on the same path, shifts 5 degrees to the left or 5 degrees to the right.
		Random rand = new Random();
		int temp = rand.nextInt(3);//random value
		if (temp==1) {//if 1 +5 degrees
			setHeading(getHeading()+5);
		} else if (temp==2) {//if 2 -5 degrees
			setHeading(getHeading()-5);
		} else {//any of the number stay of same course
			
		}
	}
	
	public void move(int tickrate){//modified move function
		super.move(tickrate);//calls move function in movable
		Random rand = new Random();
		if (getX()<0) {//calls getter for x and checks if less than 0
			setX(0);//puts drone back in bounds
			setHeading(getHeading()+rand.nextInt(90));//changes heading 
		} else if(getX()>getGameSizeX()) {// calls getter for x and checks if greater than the max gamesize
			setX(getGameSizeX());//puts drone back in bounds
			setHeading(getHeading()+rand.nextInt(90));//changes heading
		}
		if (getY()<0) {//calls getter for y and checks if less than 0
			setY(0);//puts drone back in bounds
			setHeading(getHeading()+rand.nextInt(90));//changes heading
		} else if(getY()>getGameSizeY()) {//calls getter for y and checks if greater than the max gamesize
			setY(getGameSizeY());//puts drone back in bounds
			setHeading(getHeading()+rand.nextInt(90));//changes heading
		}
	}
	
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " ";
		return "Drone:" + parentDesc + myDesc;
	}
	
	public void draw(Graphics g, Point origin) {
		int hsize= getSize()/2;
		int [] xs = { (int)getX()+(int)origin.getX(), (int)getX()+(int)origin.getX()+getSize(), (int)getX()+(int)origin.getX()+hsize };
		int [] ys = { (int)getY()+(int)origin.getY(), (int)getY()+(int)origin.getY(), (int)getY()+(int)origin.getY()+getSize()};
		g.setColor(this.getColorInt());
		g.drawPolygon(xs, ys, 3);
	}

}
