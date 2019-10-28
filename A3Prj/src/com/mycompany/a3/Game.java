package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.CMD.*;
import com.mycompany.a3.GUI.MapView;
import com.mycompany.a3.GUI.ScoreView;
import com.mycompany.a3.GUI.StyledButton;
import com.codename1.ui.events.ActionEvent;

import java.lang.String;

public class Game extends Form implements Runnable {
	private static final String ActionListener = null;
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer t;
	public static final int TICK_RATE = 20;
	private static int [] gWXY= {0,0};
	private BreakCommand myBreakCommand;
	private AccelerateCommand myAccelerateCommand;
	private TurnRightCommand myTurnRightCommand;
	private TurnLeftCommand myTurnLeftCommand;
	private ChangeStrategiesCommand myChangeStrategiesCommand;
	private Button button1;
	
	public Game() {
		t= new UITimer(this);
		gw=new GameWorld();
		mv=new MapView();
		sv=new ScoreView();
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		setLayout(new BorderLayout());
		DisplayOut myDisplayOut = new DisplayOut(gw);
		addKeyListener('m', myDisplayOut);
		///////////////////////////////////sets up the toolbar and commands for toolbar
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		myToolbar.setTitle("Robo-track Game"); 
		Toolbar.setOnTopSideMenu(false);
		ExitCommand myExitCommand = new ExitCommand();
		AccelerateCommand AccelerateCommand = new AccelerateCommand(gw);
		AboutCommand myAboutCommand = new AboutCommand();
		CheckBox checkSound = new CheckBox("Sound");
		Command mySoundCommand = new SoundCommand(gw);
		checkSound.setCommand(mySoundCommand);
		checkSound.getAllStyles().setBgTransparency(255); 
		checkSound.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		HelpCommand myHelpCommand = new HelpCommand();
		myToolbar.addComponentToSideMenu(checkSound); 
		myToolbar.addCommandToSideMenu(myExitCommand);
		myToolbar.addCommandToSideMenu(AccelerateCommand);
		myToolbar.addCommandToSideMenu(myAboutCommand); 
		myToolbar.addCommandToRightBar(myHelpCommand);
		////////////////////////////////////sets up the top container and sets it to score view
		Container topContainer = new Container(new FlowLayout(Component.CENTER));
		topContainer.add(sv);
		add(BorderLayout.NORTH,topContainer);
		////////////////////////////////////sets up the bottom container for the buttons
		Container bottomContainer = new Container(new FlowLayout(Component.CENTER));
		
		button1 =  new StyledButton("Pause");
		PauseCommand myPauseCommand = new PauseCommand(this, t, gw);
		button1.setCommand(myPauseCommand);
		addKeyListener('p', myPauseCommand);
		bottomContainer.add(button1);
		
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE)); 
		add(BorderLayout.SOUTH, bottomContainer);
		///////////////////////////////sets up the right container
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setPadding(Component.TOP, 50);
		
		Button button6 =  new StyledButton("Break");
		myBreakCommand = new BreakCommand(gw);
		button6.setCommand(myBreakCommand);
		addKeyListener('b', myBreakCommand);
		rightContainer.add(button6);
		
		Button button7 =  new StyledButton("Right");
		myTurnRightCommand = new TurnRightCommand(gw);
		button7.setCommand(myTurnRightCommand);
		addKeyListener('r', myTurnRightCommand);
		rightContainer.add(button7);
		
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE)); 
		add(BorderLayout.EAST, rightContainer);
		///////////////////////////////sets up the left container
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setPadding(Component.TOP, 50);
		
		Button button8 =  new StyledButton("Accelerate");
		myAccelerateCommand = new AccelerateCommand(gw);
		button8.setCommand(myAccelerateCommand);
		addKeyListener('a', myAccelerateCommand);
		leftContainer.add(button8);
		
		Button button9 =  new StyledButton("Left");
		myTurnLeftCommand = new TurnLeftCommand(gw);
		button9.setCommand(myTurnLeftCommand);
		addKeyListener('l', myTurnLeftCommand);
		leftContainer.add(button9);
		
		
		Button button10 = new StyledButton("Change Strategies");
		myChangeStrategiesCommand = new ChangeStrategiesCommand(gw);
		button10.setCommand(myChangeStrategiesCommand);
		leftContainer.add(button10);
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE)); 
		add(BorderLayout.WEST,leftContainer);
		/////////////////////////////sets the center container for mapview
		add(BorderLayout.CENTER, mv);
		/////////////////////////////shows and initializes gameworld
		this.show();
		gameSize(mv.getHeight(), mv.getWidth());
		gw.init(gWXY);
		t.schedule(TICK_RATE, true, this);
	}

	public void run() {
		gw.tick(TICK_RATE);
	}
	
	public static  int[] gameSize(int hight, int width) {
		gWXY[0]= width;
		gWXY[1]= hight;
		return gWXY;
	}
	
	public void enable() {
		myAccelerateCommand.setEnabled(true);
		myBreakCommand.setEnabled(true);
		myTurnRightCommand.setEnabled(true);
		myTurnLeftCommand.setEnabled(true);
		myChangeStrategiesCommand.setEnabled(true);
		removeKeyListener('a', myAccelerateCommand);
		removeKeyListener('b', myBreakCommand);
		removeKeyListener('l', myTurnLeftCommand);
		removeKeyListener('r', myTurnRightCommand);
	}
	
	public void disable() {
		myAccelerateCommand.setEnabled(false);
		myBreakCommand.setEnabled(false);
		myTurnRightCommand.setEnabled(false);
		myTurnLeftCommand.setEnabled(false);
		myChangeStrategiesCommand.setEnabled(true);
		addKeyListener('a', myAccelerateCommand);
		addKeyListener('b', myBreakCommand);
		addKeyListener('l', myTurnLeftCommand);
		addKeyListener('r', myTurnRightCommand);
	}
	public Button getButton() {
		return button1;
	}
}