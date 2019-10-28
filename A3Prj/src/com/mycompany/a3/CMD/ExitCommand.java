package com.mycompany.a3.CMD;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command {//command to exit game
	
	public ExitCommand() {
		super("Exit");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		Command cOk = new Command("Ok");
		TextField tf =  new TextField();
		Command c = Dialog.show("Enter Y/y to quit. Enter anything else to continue.", tf, cOk);
		if (c==cOk) {
			String s = tf.getText();
			if (s.equals("Y")||s.equals("y")) {
				System.exit(0);
			} else {
			}
		}
	}
}
