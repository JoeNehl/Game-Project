package com.mycompany.a3.GUI;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.mycompany.a3.GameWorld;

public class ScoreView extends Container implements Observer {

	Label tick;
	Label livesLeft;
	Label lastBaseReached;
	Label energyLevel;
	Label damageLevel;
	Label sound;
	
	public ScoreView() {//creates labels to populate score view
		tick= new Label("Tick: 0     ");
		livesLeft= new Label("Lives: 3   ");
		lastBaseReached = new Label("Base: 1   ");
		energyLevel = new Label("Energy: 50    ");
		damageLevel = new Label("Damage: 0   ");
		sound = new Label("Sound: OFF    ");
		this.add(tick);
		this.add(livesLeft);
		this.add(lastBaseReached);
		this.add(energyLevel);
		this.add(damageLevel);
		this.add(sound);
	}
	
	public void update (Observable o, Object arg) {//gets values from game
		GameWorld gw = (GameWorld)o;
		tick.setText(String.valueOf("Tick: " + gw.getTick()));
		livesLeft.setText(String.valueOf("Lives: " + gw.getLives()));
		lastBaseReached.setText(String.valueOf("Base: " + gw.getLastBaseReached()));
		energyLevel.setText(String.valueOf("Energy: " + gw.getEnergyLevel()));
		damageLevel.setText(String.valueOf("Damage: " + gw.getDamageLevel()));
		sound.setText(String.valueOf("Sound: " + returnVal(gw.getSound())));
	}
	
	private String returnVal(boolean t) {//for sound on or off display
		if (t==true) {
			return "ON";
		} else {
			return "OFF";
		}
	}
}
