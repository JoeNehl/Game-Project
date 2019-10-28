package com.mycompany.a3.GUI;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;

public class StyledButton extends Button{

	public StyledButton(String name) {//class that contains the look for all buttons
		
		super(name);
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setPadding(Component.BOTTOM, 1);
		this.getAllStyles().setPadding(Component.TOP, 1);
		this.getAllStyles().setPadding(Component.RIGHT, 2);
		this.getAllStyles().setPadding(Component.LEFT, 2);
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
		this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		this.getPressedStyle().setBgColor(ColorUtil.rgb(0,0,100));
	}
}
