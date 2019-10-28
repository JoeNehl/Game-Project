package com.mycompany.a3.CMD;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class DisplayOut extends Command {//displays map in center
	
	private GameWorld gw;
	public DisplayOut(GameWorld gw) {
		super("Left");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.map();
	}
}
