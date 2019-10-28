package com.mycompany.a3.GameObject;

import java.util.Random;

public abstract class Movable extends GameObject {
	
	private int heading;
	private int speed=0;
	
	public Movable(double x, double y, int size, int [] colors, int heading, int speed, int gWX, int gWY) {
		super (x, y, size, colors, gWX, gWY);// calls the constructor class in GameObject
		if (heading!=0) {//if statements that determine which to call, if 0 stay 0, if number randomize value
			randHeading();
		} else {
			setHeading(heading);
		}
		if (speed!=0) {
			randSpeed();
		} else {
			setSpeed(speed);
		}
	}
	
	public void randHeading() {//give random heading
		Random rand = new Random();
		this.heading = rand.nextInt(359);
	}
	
	public void randSpeed() {//gives random speed
		Random rand = new Random();
		this.speed = rand.nextInt(5)+5;
	}
	
	public int setHeading(int updHeading) {//updates heading to a given amount
		this.heading=updHeading;
		return this.heading;
	}
	
	public void setSpeed(int updSpeed) {
		this.speed=updSpeed;
	}
	
	public int getHeading() {
		return this.heading;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void move(int tickrate) {//this equation was given an correctly move the object along a current heading and at a given speed
		double theta = Math.toRadians(90-heading);
		double tempX = getX() + ((double)(Math.cos(theta))*getSpeed()*tickrate/20);
		double tempY = getY() + ((double)(Math.sin(theta))*getSpeed()*tickrate/20);
		setX(tempX);
		setY(tempY);
	}
	
	public String toString() {
		String parentDesc = super.toString();//calls the toString function in the GameObject class
		String myDesc = " Heading=" + getHeading() + " Speed=" + getSpeed();
		return parentDesc + myDesc;//returns the string of the parent value and the string value of this class
	}
}
