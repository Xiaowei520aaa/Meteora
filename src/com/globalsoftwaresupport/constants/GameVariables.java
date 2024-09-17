package com.globalsoftwaresupport.constants;

public class GameVariables {

	private GameVariables() {
		
	}
	
	// Java forces the OS not to cache this variables
	// it is read from the memory always
	public volatile static boolean IN_GAME = true;
	// the meteors can hit the ship 10x before game over
	public volatile static int SHIELDS = 3;
	public volatile static int SCORE = 0;
}
