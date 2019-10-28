package com.mycompany.a3.CMD;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SoundCommand extends Command{//command to turn sound off and on
	private GameWorld gw;
	public SoundCommand(GameWorld gw) {
		super("Sound");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(((CheckBox)ev.getComponent()).isSelected()) {
			gw.sound(true);
		} else {
			gw.sound(false);
		}
		SideMenuBar.closeCurrentMenu();
	}
}
