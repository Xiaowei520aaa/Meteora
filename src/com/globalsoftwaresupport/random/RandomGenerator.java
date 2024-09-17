package com.globalsoftwaresupport.random;

import java.util.Random;

import com.globalsoftwaresupport.constants.Constants;

public class RandomGenerator {

	private Random random;
	
	public RandomGenerator() {
		this.random = new Random();
	}
	
	public boolean isMeteorGenerated() {
		return random.nextDouble() < Constants.METEOR_PROBABILITY;
	}

	// we want to generate a random integer in the range 
	// [Constants.SHIP_WIDTH, GAME_WIDTH-SHIP_WIDTH]
	public int generateRandomX() {
		return random.nextInt(Constants.GAME_WIDTH-2*Constants.SHIP_WIDTH)
				+ Constants.SHIP_WIDTH;
	}
}
