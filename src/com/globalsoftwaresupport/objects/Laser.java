package com.globalsoftwaresupport.objects;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.globalsoftwaresupport.constants.Constants;
import com.globalsoftwaresupport.image.ImageFactory;
import com.globalsoftwaresupport.image.ImageType;

public class Laser extends Sprite {

	public Laser() {
		
	}
	
	public Laser(int x, int y) {
		this.x = x;
		this.y = y;
		initialize();
	}
	
	private void initialize() {
		// initialize the image
		ImageIcon image = ImageFactory.createImage(ImageType.LASER);
		setImage(image.getImage());
		
		// set the (x,y) position
		setX(x + Constants.SHIP_WIDTH/2 - Constants.LASER_WIDTH/2);
		setY(y - Constants.LASER_HEIGHT);
	}

	@Override
	protected void update() {
		// laser beams travels vertically from the spaceship towards
		// the upper region of the canvas
		y -= Constants.LASER_SHOT_SPEED;
		
		// if the laser is outside the scope of the screen then we have to
		// remove it
		if(y < 0)
			die();
	}

	@Override
	protected void act(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}
}
