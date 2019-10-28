package com.mycompany.a3.CMD;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TurnRightCommand extends Command {//command to make player robot turn right

	private GameWorld gw;
	public TurnRightCommand(GameWorld gw) {
		super("Right");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.turnRight();
	}
}
