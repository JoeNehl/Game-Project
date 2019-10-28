package com.mycompany.a3.CMD;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public class PauseCommand extends Command{
	
	private GameWorld gw;
	private Game g;
	private UITimer t;
	private boolean paused = false;
	
	public PauseCommand(Game g, UITimer t, GameWorld gw) {
		super("pause");
		this.gw=gw;
		this.t=t;
		this.g=g;
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		paused=!paused;
		if (paused==true) {
			t.cancel();
			gw.pause(paused);
			g.disable();
			g.getButton().setText("play");
		}else {
			t.schedule(g.TICK_RATE, true, g);
			gw.pause(paused);
			g.enable();
			g.getButton().setText("pause");
		}
	}

}
