package com.mycompany.a3;

import java.io.*;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class SoundEffect {
	private Media sf;
	
	public SoundEffect(String file) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+file);
			sf = MediaManager.createMedia(is, "audio/wav");
			sf.setVolume(100);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		sf.setTime(0);
		sf.play();
	}
	
	public void stop() {
		sf.pause();
	}
}
