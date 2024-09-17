package com.globalsoftwaresupport.constants;

public class Constants {

	// we can not instantiate this class
	private Constants() {
		
	}
	
	public static final int GAME_WIDTH = 420;
	public static final int GAME_HEIGHT = 746;
	
	public static final int SHIP_WIDTH = 75;
	public static final int SHIP_HEIGHT = 104;
	
	public static final int LASER_WIDTH = 12;
	public static final int LASER_HEIGHT = 62;
	
	public static final int METEOR_WIDTH = 48;
	public static final int METEOR_HEIGHT = 82;
	
	public static final int LASER_SHOT_SPEED = 4;
	
	public static final double METEOR_PROBABILITY = 0.05;
	public static final int METEOR_SPEED = 2;
	
	public static final String GAME_TITLE = "Meteora";
	
	// constants for the images
	public static final String LOGO_URL = "resources/images/meteor.png";
	public static final String BACKGROUND_URL = "resources/images/background.jpg";
	public static final String LASER_URL = "resources/images/laser_shot.png";
	
	// speed of the game (in FPS)
	// it is equivalent to 67 FPS
	public static final int GAME_SPEED = 15;
	
	public static final String GAME_OVER = "GAME OVER";
}
