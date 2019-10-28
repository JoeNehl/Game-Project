package com.mycompany.a3.GameObject;

public abstract class Robot extends Movable implements ISteerable {
	
	private int steeringDirection;
	private int maximumSpeed=10;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel=0;
	private int lastBaseReached=1;
	private static int heading;
	private static int speed=1;
	
	public Robot(int x, int y, int size, int [] colors, int gWX, int gWY) {
		super(x, y, size, colors, heading, speed, gWX, gWY);//calls the constructor class in movable
		this.energyLevel=2000;
		this.energyConsumptionRate=1;
	}
	
	public Robot(int x, int y, int size, int energyLevel, int damageLevel, int [] color, int gWX, int gWY) {
		super(x, y, size, color, heading, speed, gWX, gWY);
		this.energyLevel=200;
		this.energyConsumptionRate=1;
	}

	public boolean steerRight() {
		int temp = 40+getHeading();//creates a temp value checking that the steeringdirection is not more that 40 degrees to the right
		if (temp>355) {// this and the next line check to make sure that the value of temp is never over 359 since 0-359 degrees is the bound. if it is over sets it to the correct number equivalent
			temp=(getHeading()+40)-360;
		}
		if (this.steeringDirection != (temp)) {//checks to see if steering directions is under the max 40 degree change
			this.steeringDirection = this.steeringDirection + 5;//adds 5 to steeringDirection
			if (this.steeringDirection==360) {//if steering direction is 360 then sets to 0 (since max bound is 359)
				this.steeringDirection = 0;
			}
		} else {
			return true;
		}
		return false;
	}
	
	public boolean steerLeft() {
		int temp = getHeading()-40;//creates a temp value checking that the steeringdirection is not more that 40 degrees to the left
		if (temp<0) {// this and the next line check to make sure that the value of temp is never under 0 since 0-359 degrees is the bound. if it is over sets it to the correct number equivalent
			temp=(getHeading()-40)+360;
		}
		if (this.steeringDirection != (temp)) {//checks to see if steering directions is under the max 40 degree change
			this.steeringDirection = this.steeringDirection - 5;//subtracts 5 to steeringDirection
			if (this.steeringDirection < 0){//if steering direction under 0 then sets to 355 (since min bound is 0 and decreases by 5 at a time)
				this.steeringDirection = 355;
			}
		} else {
			return true;
		}
		return false;
	}
	
	/*public void steerLeft(int num)
	{
		this.steeringDirection = this.steeringDirection - num;
		if (this.steeringDirection < 0){//if steering direction under 0 then sets to 355 (since min bound is 0 and decreases by 5 at a time)
			this.steeringDirection = 360-num;
		}
	}
	
	public void steerRight(int num) {
		this.steeringDirection = this.steeringDirection - num;
		if (this.steeringDirection < 0){//if steering direction under 0 then sets to 355 (since min bound is 0 and decreases by 5 at a time)
			this.steeringDirection = 360-num;
		}
	}*/
	
	public boolean checkEnergy() {
		if (getEnergyLevel()<=0){
			return true;
		} else {
			return false;
		}
	}
	
	public void setEnergy(int added) {
		this.energyLevel=this.energyLevel+added;//used for when robot collides with energy station
	}
	
	public void decEnergy() {
		this.energyLevel = this.energyLevel-this.energyConsumptionRate;//used when robot moves, decreases max energy
	}
	
	public boolean increaseSpeed() {
		if (getSpeed()==maximumSpeed) {//if speed is = to max speed do nothing
			return true;
		} else {
			setSpeed(getSpeed()+1);//else increases speed
			return false;
		}
	}
	
	public boolean decreaseSpeed() {//if speed = 0 do nothing
		if (getSpeed()==0) {
			return true;
		} else {
			setSpeed(getSpeed()-1);//else speed decreases
			return false;
		}
	}
	
	public void fadeColor(int number) {
		int colorR = getColor()[0];
		int colorG = getColor()[1];
		int colorB = getColor()[2];
		if (this instanceof RobotPlayer) {
			if ((colorR - (25*number)) > 0) {
				colorR = colorR - (25*number);
			}
			if ((colorG - (25*number)) > 0) {
				colorG = colorG - (25*number);
			}
			if ((colorB - (25*number)) > 0) {
				colorB = colorB - (25*number);
			}
			int [] newColor = {colorR, colorG, colorB};
			this.setColor(newColor);
		}
	}
	
	public void setMax() {
		if (getSpeed()>maximumSpeed) {//checks to see if speed is over maximum, used when robot takes damage and maximum speed is reduced
			setSpeed(maximumSpeed);
		}
	}
	
	public void resetValues() {//resets the changeable values of robot
		int [] reset = {000,000,225};
		this.maximumSpeed=10;
		this.damageLevel=0; 
		this.energyLevel=20000;
		for (int i=0; i<5; i++) {
			increaseSpeed();
		}
		setColor(reset);
	}
	
	public boolean damage(int number) {
		this.damageLevel=this.damageLevel+number;//takes the damage value passed (number) and adds it to current damage
		this.maximumSpeed=this.maximumSpeed-number;//changes the max speed relative to damage
		setMax();
		fadeColor(number);//calls the color class to fade due to damage
		if (getDamageLevel()>=10){//checks to see if robot is at max damage
			//resetValues();//resets the values of robot
			return true;
		} else {
			return false;
		}
	}
	
	public void setLastBaseReached(int base) {
		this.lastBaseReached=base;//sets the base to the robots current base
	}
	
	public int getLastBaseReached() {
		return this.lastBaseReached;
	}
	
	public int getDamageLevel() {
		return this.damageLevel;
	}
	
	public int getEnergyLevel() {
		return this.energyLevel;
	}
	
	public int getMaximumSpeed() {
		return this.maximumSpeed;
	}
	
	public int getSteeringDirection() {
		return this.steeringDirection;
	}
	
	public void move(int tickrate) {
		setHeading(this.steeringDirection);//updates the actual value for heading (with the users intended change) before the robot moves
		super.move(tickrate);// calls the move function in movable
		decEnergy();//updates the Energy the robot has left after it moves
		if ((this instanceof RobotPlayer) || (this instanceof RobotAI)) {
			if (this.getX()>this.getGameSizeX()) {
				this.setX(this.getGameSizeX());
			}else if(this.getX()<0) {
				this.setX(0);
			}
			if (this.getY()>this.getGameSizeY()) {
				this.setY(this.getGameSizeY());
			}else if(this.getY()<0) {
				this.setY(0);
			}
		}
	}
	
	public void handleCollision(GameObject check) {
		if(check instanceof EnergyStation) {
			int temp = ((EnergyStation)check).getCapacity();
			((EnergyStation)check).fade();
			this.setEnergy(temp);
			((EnergyStation)check).zeroCapacity();
			System.out.println("Robot gained " + temp + " energy.");
		}else if(check instanceof Drone) {
			this.damage(1);
		}else if(check instanceof Base) {
			int temp=((Base)check).checkCollision();
			if (temp==(this.lastBaseReached+1)) {
				this.setLastBaseReached(temp);
			}
		}else if(check instanceof Robot) {
			this.damage(2);
		}else {
			this.damage(2);
		}
	}
	
	public String toString() {
		String parentDesc = super.toString();//calls the toString function in the movable class
		String myDesc = " damageLevel=" + getDamageLevel() +" lastBaseReached=" + getLastBaseReached() + " energyLevel=" + getEnergyLevel() + " steeringDirection=" + this.steeringDirection + " maxSpeed=" + this.maximumSpeed;
		return parentDesc + myDesc;//returns the string of the parent value and the string value of this class
	}
	
	public boolean checkLife() {
		if (this.damageLevel>=10) {
			return true;
		}
		return false;
	}
}
