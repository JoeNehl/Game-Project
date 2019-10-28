package com.mycompany.a3.GameObject;

public abstract class Fixed extends GameObject {

	public Fixed(double x, double y, int size, int[] colors, int gWX, int gWY) {
		super(x, y, size, colors, gWX, gWY);// calls the constructor class in GameObject
	}
	
	public String toString() {
		String parentDesc = super.toString();//calls the toString function in the GameObject class
		String myDesc = " ";
		return parentDesc + myDesc;//returns the string of the parent value and the string value of this class
	}
}
