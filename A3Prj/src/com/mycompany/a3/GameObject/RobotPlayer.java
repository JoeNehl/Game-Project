package com.mycompany.a3.GameObject;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class RobotPlayer extends Robot {
	
	public static RobotPlayer myRobot;

	private RobotPlayer(int x, int y, int size, int [] color, int gWX, int gWY) {
		super(x, y, size, color, gWX, gWY);
	}
	
	public static RobotPlayer getRobot(int x, int y, int size, int [] color, int gWX, int gWY) {//this implements the singleton 
		if (myRobot==null) {
			myRobot=new RobotPlayer(x, y, size, color, gWX, gWY);
		}
		return myRobot;
	}
	
	public static RobotPlayer getRobot() {
		return myRobot;
	}
	
	public String toString() {
		String parentDesc = super.toString();
		return "RobotPlayer: " + parentDesc;
	}
	
	public void draw(Graphics g, Point origin) {
		int hsize= getSize()/2;
		int x= (int)getX()+(int)origin.getX()-hsize;
		int y= (int)getY()+(int)origin.getY()-hsize;
		g.setColor(this.getColorInt());
		g.fillRect(x, y, getSize(), getSize());
	}
}
