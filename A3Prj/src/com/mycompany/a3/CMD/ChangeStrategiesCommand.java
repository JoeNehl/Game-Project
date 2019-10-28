package com.mycompany.a3.CMD;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ChangeStrategiesCommand extends Command {//command to change the robot ai strategies
	private GameWorld gw;
	public ChangeStrategiesCommand(GameWorld gw) {
		super("Change Strategy");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.changeStrategy();
	}
}
