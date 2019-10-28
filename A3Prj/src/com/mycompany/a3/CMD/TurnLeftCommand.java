package com.mycompany.a3.CMD;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TurnLeftCommand extends Command{//command to make player robot turn left

	private GameWorld gw;
	public TurnLeftCommand(GameWorld gw) {
		super("Left");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.turnLeft();
	}
}
