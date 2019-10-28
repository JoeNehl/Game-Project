package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;
import com.mycompany.a3.GameObject.Base;
import com.mycompany.a3.GameObject.Drone;
import com.mycompany.a3.GameObject.EnergyStation;
import com.mycompany.a3.GameObject.GameObject;
import com.mycompany.a3.GameObject.Movable;
import com.mycompany.a3.GameObject.Robot;
import com.mycompany.a3.GameObject.RobotAI;
import com.mycompany.a3.GameObject.RobotPlayer;
import com.mycompany.a3.Iterator.GameObjectCollection;
import com.mycompany.a3.Iterator.IIterator;
import com.mycompany.a3.SoundEffect;

public class GameWorld extends Observable {
	
	private static int Tick=0;//gameclock value
	private static int lives=3;//lives remaining
	private static boolean isDead=false;//used to check of player has died
	private static boolean sound=false;//determines if sound is off or on
	private GameObjectCollection gameList = new GameObjectCollection();//storage system for Gameobjects
	private final int [] PLAYER_COLOR = {000, 000, 225};
	private final int [] BASE_COLOR = {225, 000, 225};
	private final int [] AI_COLOR = {000, 000, 255};
	private final int [] DRONE_COLOR = {225, 000, 000};
	private final int [] ENERGY_COLOR = {000, 225, 000};
	private static int gWX =0;
	private static int gWY =0;
	private SoundEffect crash = new SoundEffect("crash.wav");
	private SoundEffect blip = new SoundEffect("energygrab.wav");
	private SoundEffect success = new SoundEffect("success.wav");
	private SoundEffect dead =  new SoundEffect("death.wav");
	private BGM bgm = new BGM("music.wav");
	
	
	public void init(int [] gWXY) {//starts the game by spawning in gameobjects
		
		gWX=gWXY[0];
		gWY=gWXY[1];
		gameList = new GameObjectCollection();
		createRobotPlayer();//Creates a player robot in array spot (0)
		createRobotAI();// creates 3 robots who spawn around the player
		createBase();// creates 4 bases
		for (int i=0; i<maxRand(5); i++) {//creates a random number of energy stations (up to 5)
			createEnergy();
		}
		for (int i=0; i<maxRand(5); i++) {//creates a random number of drones (up to 5)
		createDrone();
		}
	}
	
	public int maxRand(int r){
		Random rand = new Random();
		int num = rand.nextInt(r)+2;//makes sure there are at lease two of random creation types
		return num;
	}
	
	private void createRobotAI() {
		gameList.add(new RobotAI(200,200,50,AI_COLOR,gWX,gWY));
		gameList.add(new RobotAI(400,1000,50,AI_COLOR,gWX,gWY));
		gameList.add(new RobotAI(500,300,50,AI_COLOR,gWX,gWY));
		notifyObservers();
	}
	
	private void createBase() {//creates 4 bases with specified values
		gameList.add(new Base(25,25,50,1,BASE_COLOR,gWX,gWY));
		gameList.add(new Base(200,450,50,2,BASE_COLOR,gWX,gWY));
		gameList.add(new Base(1000,300,50,3,BASE_COLOR,gWX,gWY));
		gameList.add(new Base(750,750,50,4,BASE_COLOR,gWX,gWY));
		notifyObservers();
	}
	
	private void createRobotPlayer() {//creates robot with specified values
		if (RobotPlayer.getRobot()==null) {
			this.gameList.add(RobotPlayer.getRobot(25,25,50,PLAYER_COLOR,gWX,gWY));
		} else {
			System.out.println("A PlayerRobot already exists");
		}
		notifyObservers();
	}
	
	private void createEnergy() {//creates energy station
		this.gameList.add(new EnergyStation(ENERGY_COLOR,gWX,gWY));
		notifyObservers();
	}
	
	private void createDrone() {//creates drone
		this.gameList.add(new Drone(DRONE_COLOR,gWX,gWY));
		notifyObservers();
	}
	
	public void accelerate() {//calls function to accelerate robot
		boolean check=false;
		IIterator elements = gameList.getIterator();
		while (elements.hasNext()) {
			GameObject rp = (GameObject)elements.getNext();
			if (rp instanceof RobotPlayer) {
				check=((RobotPlayer)rp).increaseSpeed();
			}
		}
		if (check==true) {
			System.out.println("Speed already at Max.");
		} else {
			System.out.println("Speed increased.");
		}
	}
	
	public void deccelerate() {//calls function to decelerate robot
		boolean check=false;
		IIterator elements = gameList.getIterator();
		while (elements.hasNext()) {
			GameObject rp = (GameObject)elements.getNext();
			if (rp instanceof RobotPlayer) {
				check=((RobotPlayer)rp).decreaseSpeed();
			}
		}
		if (check==true) {
			System.out.println("Speed already at 0.");
		} else {
			System.out.println("Speed decreased.");
		}
	}
	
	public void turnLeft() {//calls function to turn left
		boolean value=false;
		IIterator elements = gameList.getIterator();
		while (elements.hasNext()) {
			GameObject rp = (GameObject)elements.getNext();
			if (rp instanceof RobotPlayer) {
				value=((RobotPlayer)rp).steerLeft();
			}
		}
		if (value==false) {
			System.out.println("Robot turned left.");
		}else {
			System.out.println("Robot is at max left turn");
		}
	}
	
	public void turnRight() {//calls function to turn right
		boolean value=false;
		IIterator elements = gameList.getIterator();
		while (elements.hasNext()) {
			GameObject rp = (GameObject)elements.getNext();
			if (rp instanceof RobotPlayer) {
				((RobotPlayer)rp).steerRight();
			}
		}
		if (value==false) {
			System.out.println("Robot turned right.");
		}else {
			System.out.println("Robot is at max right turn");
		}
	}
	
	public void map() {//outputs all objects and their info
		IIterator elements = gameList.getIterator();
		while (elements.hasNext()) {
			GameObject o = (GameObject)elements.getNext();
			System.out.println(o +"\n");
		}
	}
	
	public void checkCollision() {
		IIterator elements = gameList.getIterator();
		while (elements.hasNext()) {
			GameObject r = (GameObject)elements.getNext();
			if (r instanceof Robot) {
				IIterator elements2 = gameList.getIterator();
				while (elements2.hasNext()) {
					GameObject o = (GameObject)elements2.getNext();
					if (r!=o) {
						Robot robot = (Robot)r;
						if (((robot.collidesWith(o))==true) && ((o.collidesWith(robot))==true)) {
							if ((o instanceof EnergyStation) && (((EnergyStation)o).getCapacity()!=0)) {
								if (sound==true) {
									blip.play();
								}
								createEnergy();
							}else if((o instanceof RobotAI) || (o instanceof Drone)){
								if (sound==true) {
								crash.play();
								}
							}else if((o instanceof Base && (((Base)o).checkCollision()==((Robot)r).getLastBaseReached()+1))){
								if (sound==true) {
								success.play();
								}
							}
							robot.handleCollision(o);
						}
					}
					notifyObservers();
				}
			}
		}
	}
	
	public void tick(int tickrate) {//increases GameWorld by one tick
		Tick++;
		System.out.println("Game has advanced one tick.");
		IIterator elements = gameList.getIterator();
		while (elements.hasNext()) {
			GameObject rp = (GameObject)elements.getNext();
			if (rp instanceof RobotPlayer) {
				isDead=((RobotPlayer)rp).checkLife();
				if (!isDead) {//checks to see if robot is dead
					IIterator elements2 = gameList.getIterator();
					while (elements2.hasNext()) {
						GameObject m = (GameObject)elements2.getNext();
						if (m instanceof RobotAI) {
							((RobotAI) m).invokeStrategy();
							notifyObservers();
						}
						if (m instanceof Movable) {
							((Movable)m).move(tickrate);
							notifyObservers();
						}
					}
				} else {//if player is dead
					if(lives!=0) {//checks to see if player has lives left and resets robot values
						System.out.println("Player has died and lost a life.");
						lives--;//takes away a life
						if (sound==true) {
							dead.play();
						}
						notifyObservers();
						((RobotPlayer) rp).resetValues();
						notifyObservers();
						isDead=false;
					} else {//player has no lives left and game quits
						System.out.println("Player has died and lost the game...");
						System.exit(0);
					}
				}
				isDead = ((RobotPlayer)rp).checkEnergy();//checks to see if robot is out of energy
				if (!isDead) {//if not then check to see if robot has reached the last base in the game
					((RobotPlayer)rp).getLastBaseReached();
					if(((RobotPlayer)rp).getLastBaseReached()>=4) {//player win case
						System.out.println("Player has reached the last base. Winner! Ticks:" + Tick);
						System.exit(0);
					}
				} else {//robot is out of energy
					if(lives!=0) {//lives are not zero then game continues
						System.out.println("Player has run out of energy and lost a life.");
						lives--;
						((RobotPlayer) rp).resetValues();
						notifyObservers();
						isDead=false;
					} else {//player is out of lives and game ends
						System.out.println("Player has run out of energy and lost the game...");
						System.exit(0);
					}
				}
			}else if(rp instanceof RobotAI) {
				((RobotAI)rp).getLastBaseReached();
				if(((RobotAI)rp).getLastBaseReached()>=4) {//npc winner case
					System.out.println("NPC has reached the last base. You Lose... Ticks:" + Tick);
					System.exit(0);
				}
			}
		}
		checkCollision();
	}
	
	public void sound(boolean click) {//sets the sound on or off
		if (click==true) {
			sound=true;
			System.out.println("Sound is on.");
			bgm.play();
			notifyObservers();
		}else {
			sound=false;
			System.out.println("Sound is off.");
			bgm.pause();
			notifyObservers();
		}
	}
	
	public void changeStrategy() {//changes the strategy in robot ai
		IIterator elements = gameList.getIterator();
		while (elements.hasNext()) {
			GameObject npr = (GameObject)elements.getNext();
			if (npr instanceof RobotAI) {
				((RobotAI) npr).setStrategy();
			}
		}
		System.out.println("NPC's have changed Strategies and last base reached has increased");
	}
	
	public boolean getSound() {//returns sound
		return sound;
	}
	
	public int getLives() {//returns lives
		return lives;
	}
	
	public int getTick() {//returns tick
		return Tick;
	}
	
	public int getLastBaseReached() {//returns last base reached
		int temp=1;
		IIterator elements = gameList.getIterator();
		while (elements.hasNext()) {
			GameObject rp = (GameObject)elements.getNext();
			if (rp instanceof RobotPlayer) {
				temp=((Robot) rp).getLastBaseReached();
			}
		}
		return temp;
	}
	
	public int getEnergyLevel() {//returns energy level
		int temp=50;
		IIterator elements = gameList.getIterator();
		while (elements.hasNext()) {
			GameObject rp = (GameObject)elements.getNext();
			if (rp instanceof RobotPlayer) {
				temp=((Robot) rp).getEnergyLevel();
			}
		}
		return temp;
	}
	
	public int getDamageLevel() {//returns robot damage
		int temp=50;
		IIterator elements = gameList.getIterator();
		while (elements.hasNext()) {
			GameObject rp = (GameObject)elements.getNext();
			if (rp instanceof RobotPlayer) {
				temp=((Robot) rp).getDamageLevel();
			}
		}
		return temp;
	}
	
	public double[] getBase(int current) {
		int num;
		double [] xy = {0,0};
		IIterator elements = gameList.getIterator();
		while (elements.hasNext()) {
			GameObject b = (GameObject)elements.getNext();
			if (b instanceof Base) {
				num=((Base) b).checkCollision();
				if(num==current) {
					xy[0]=((Base) b).getX();
					xy[1]=((Base) b).getY();
					return xy;
				}
			}
		}
		return xy;
	}
	
	public GameObjectCollection getCollection() {
		return this.gameList;
	}
	
	public void pause(boolean pause) {
		if(getSound()==true) {
			if(pause==true) {
				bgm.pause();
			}else {
				bgm.play();
			}
		}
	}
	
	@Override
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}
}
