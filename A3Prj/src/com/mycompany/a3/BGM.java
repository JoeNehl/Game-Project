package com.mycompany.a3;

import java.io.*;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGM implements Runnable {
	private Media BGM;
	
	public BGM (String file) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+file);
			BGM = MediaManager.createMedia(is, "audio/wav", this);
			BGM.setVolume(50);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pause() {
		BGM.pause();
	}
	
	public void play() {
		BGM.play();
	}
	
	public void run() {
		BGM.setTime(0);
		BGM.play();
	}
}
