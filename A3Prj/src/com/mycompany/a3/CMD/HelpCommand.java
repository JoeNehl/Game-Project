package com.mycompany.a3.CMD;

import com.codename1.ui.Command;//displays help screen
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command {
	
	public HelpCommand() {
		super("Help");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		Command cOk = new Command("Close");
		TextArea myText = new TextArea(	"Commannd:           Key:                    Description:\n"
				+ 						"Accelerate:             a                       Player robot increases speed.\n"
				+ 						"Brake:                    b                       Player robot descreases speed.\n"
				+ 						"Turn left:                l                       Player robot turns left.\n"
				+ 						"Turn right:              r                       Player robot turns right.\n");
		Command c = Dialog.show("Help", myText, cOk);
		if (c==cOk) {
			
		}
	}
}
