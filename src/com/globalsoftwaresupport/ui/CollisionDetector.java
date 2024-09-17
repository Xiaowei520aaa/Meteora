package com.globalsoftwaresupport.ui;

import com.globalsoftwaresupport.constants.Constants;
import com.globalsoftwaresupport.objects.Sprite;

public class CollisionDetector {

	// detect the collisions between the meteors and the laser beams
	// it returns true if there is a collision
	public boolean collisionLaserMeteor(Sprite laser, Sprite meteor) {
		
		int shotX = laser.getX();
		int shotY = laser.getY();
		int meteorX = meteor.getX();
		int meteorY = meteor.getY();
		
		return (shotX < meteorX + Constants.METEOR_WIDTH &&
				meteorX < shotX + Constants.LASER_WIDTH &&
					shotY < meteorY + Constants.METEOR_HEIGHT &&
						meteorY < shotY + Constants.LASER_HEIGHT);		
	}
	
	// detect the collisions between the meteors and the spaceship
	// it returns true if there is a collision
	public boolean collisionMeteorSpaceShip(Sprite meteor, Sprite spaceShip) {
			
		int meteorX = meteor.getX();
		int meteorY = meteor.getY();
		int shipX = spaceShip.getX();
		int shipY = spaceShip.getY();
			
		return (shipX < meteorX + Constants.METEOR_WIDTH &&
				meteorX < shipX + Constants.SHIP_WIDTH &&
					shipY < meteorY + Constants.METEOR_HEIGHT &&
						meteorY < shipY + Constants.SHIP_HEIGHT);		
	}
}
