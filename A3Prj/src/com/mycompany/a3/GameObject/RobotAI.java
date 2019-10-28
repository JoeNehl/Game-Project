package com.mycompany.a3.GameObject;

import java.util.Random;
import com.mycompany.a3.GameWorld;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.codename1.util.MathUtil;

import java.lang.Math;

public class RobotAI extends Robot implements IStrategy{
	
	private int currentStrat;
	private static int energyLevel;
	private static int damageLevel;
	private GameWorld gw;

	public RobotAI(int x, int y, int size, int [] color, int gWX, int gWY) {
		super(x, y, size, energyLevel, damageLevel, color, gWX, gWY);
		Random rand = new Random();
		currentStrat=rand.nextInt(3);
	}
	
	public void move(int tickrate) {
		invokeStrategy();
		super.move(tickrate);
		refillEnergy();
	}
	
	public void refillEnergy() {
		if (this.getEnergyLevel()<20) {
			this.setEnergy(200);
		}
	}
	
	public void StrategyBase() {//ai goes towards the next base in their list
		int optimal=0;
		int [] xy = {0,0}; 
		int current=this.getHeading();//gets current heading
		int currentBase=this.getLastBaseReached()+1;//gets the next base needed
		if (currentBase==2) {
			xy[0]=200;
			xy[1]=450;
		}else if(currentBase==3) {
			xy[0]=1000;
			xy[1]=300;
		}else {
			xy[0]=750;
			xy[1]=750;
		}
		if (this.getSpeed()<this.getMaximumSpeed()) {//increases max speed
			this.setSpeed(this.getMaximumSpeed());
		}
		double x = this.getX();
		double y = this.getY();
		double x2 = xy[0];
		double y2 = xy[1];
		double beta= MathUtil.atan2((x2-x),(y2-y));//gets needed heading
		beta = Math.toDegrees(beta);//converts to degrees
		if (beta<0) {//fixes angle
			optimal = (int) beta+360;
		}else if(beta>360){
			optimal = (int) beta-360;
		}else {
			optimal = (int) beta;
		}
		if  (optimal+360>current+360) {//checks to see if turning right or left is more optimal
			int heading=(optimal-current)/5;//calculates how many turns it needs
			for (int i=0; i<(heading/5); i++)
				this.steerRight();
		}else {
			int heading=(current-optimal)/5;
			for (int i=0; i<(heading/5); i++)
				this.steerLeft();
		}
	}
	
	public void StrategyRobot() {
		int optimal=0;
		int current=this.getHeading();//gets current heading
		int temp = 0;
		RobotPlayer rp = RobotPlayer.getRobot();//gets info from player robot
		if (this.getSpeed()<this.getMaximumSpeed()) {//increases max speed
			this.setSpeed(this.getMaximumSpeed());
		}
		double x = this.getX();
		double y = this.getY();
		double x2 = rp.getX();
		double y2 = rp.getY();
		double beta= MathUtil.atan2((x2-x),(y2-y));//gets needed heading
		beta = Math.toDegrees(beta);//converts to degrees
		if (beta<0) {//fixes angle
			optimal = (int) beta+360;
		}else if(beta>360){
			optimal = (int) beta-360;
		}else {
			optimal = (int) beta;
		}
		if  ((optimal+180)>(current+180)) {//checks to see if turning right or left is more optimal
			int heading=(optimal-current)/5;//calculates how many turns it needs
			for (int i=0; i<(heading); i++) {
				this.steerRight();
				temp = heading-5;
			}
			if (temp <0 && temp>-5) {
				//steerLeft(temp);
			}
		}else {
			int heading=(current-optimal)/5;
			for (int i=0; i<(heading); i++) {
				this.steerLeft();
				temp = heading+5;
			}
			if (temp>359 && temp<5) {
				//steerRight(temp);
			}
		}
	}
	
	public void StrategyRandom() {//puts the robot on a random path
		Random rand = new Random();
		int i = rand.nextInt(2);
		int j = rand.nextInt(10);
		int k = rand.nextInt(2);
		int l = rand.nextInt(5);
		if (k==1) {
			for (int m=1; m<l; m++) {
				this.increaseSpeed();
			}
		} else {
			for (int m=1; m<l; m++) {
				this.decreaseSpeed();
			}
		}
		if (i==1) {
			for (int n=1; n<j;n++) {
				this.steerLeft();			
				}	
		} else {
			for (int n=1; n<j;n++) {
				this.steerRight();
			}
		}
	}
	
	public void setStrategy() {//sets a new strategy for the robot
		currentStrat++;
		currentStrat=((currentStrat%3)+1);
		int i=getLastBaseReached();
		setLastBaseReached(i+1);
	}
	
	public void invokeStrategy() {//calls the right strategy
		if (currentStrat==1) {
			StrategyRandom();
		}else if(currentStrat==2) {
			StrategyRobot();
		}else {
			StrategyBase();
		}
	}
	public String returnStrat() {//returns what strategy the robot is using
		if (currentStrat==1) {
			return "Random";
		}else if(currentStrat==2) {
			return "ChaseRobot";
		}else {
			return "ChaseBase";
		}
	}
	
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " CurrentStrategy:" + returnStrat();
		return "RobotAI: " + parentDesc + myDesc;
	}
	
	public void draw(Graphics g, Point origin) {
		int hsize= getSize()/2;
		int x= (int)getX()+(int)origin.getX()-hsize;
		int y= (int)getY()+(int)origin.getY()-hsize;
		g.setColor(this.getColorInt());
		g.drawRect(x, y, getSize(), getSize());
	}
}
