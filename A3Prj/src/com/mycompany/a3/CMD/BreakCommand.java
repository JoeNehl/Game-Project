package com.mycompany.a3.CMD;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class BreakCommand extends Command{//command linking break to gui

	private GameWorld gw;
	public BreakCommand(GameWorld gw) {
		super("Break");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.deccelerate();
	}
}
