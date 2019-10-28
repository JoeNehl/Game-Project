package com.mycompany.a3.CMD;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {//command structure for displaying information about program
	
	public AboutCommand() {
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		Command cOk = new Command("Close");
		Label label = new Label("Created by Joseph Nehl\n"+"CSC133\n"+"Version 2");
		Command c = Dialog.show("About info", label, cOk);
		if (c==cOk) {
			
		}
	}
}
