package com.globalsoftwaresupport.objects;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.globalsoftwaresupport.constants.Constants;

public class SpaceShip extends Sprite {

	// the user is able to move the spaceship on the x axis (horizontally)
	private int dx;
	private int dy;
	private List<ImageIcon> frames;
	private int imageIndex;
	
	public SpaceShip() {
		frames = new ArrayList<>();
		frames.add(new ImageIcon("resources/images/spaceship_0.png"));
		frames.add(new ImageIcon("resources/images/spaceship_1.png"));
		frames.add(new ImageIcon("resources/images/spaceship_2.png"));
		frames.add(new ImageIcon("resources/images/spaceship_3.png"));
		
		// set the first frame as the initial image
		setImage(frames.get(0).getImage());
		
		// set the initial (x,y) location of the spaceship
		int initialX = Constants.GAME_WIDTH/2 - Constants.SHIP_WIDTH/2;
		int initialY = Constants.GAME_HEIGHT - Constants.SHIP_HEIGHT - 10;
		
		setX(initialX);
		setY(initialY);
	}
	
	@Override
	protected void update() {
		
		// update the location (x coordinate) of the ship
		x += dx;
		y += dy;
		
		if(y <= 0)
			y = 0;
		
		if(y >= Constants.GAME_HEIGHT-Constants.SHIP_HEIGHT)
			y = Constants.GAME_HEIGHT-Constants.SHIP_HEIGHT;
		
		// check the boundaries
		//can not go beyond the canvas on the left side
		if(x <= 0)
			x = 0;
		
		//can not go beyond the canvas on the right
		if(x >= Constants.GAME_WIDTH-Constants.SHIP_WIDTH)
			x = Constants.GAME_WIDTH-Constants.SHIP_WIDTH;
		
		// update the frame (actual image) in every iteration
		imageIndex++;
		
		// reinitialize the index if it is out of bound
		if(imageIndex > frames.size()-1)
			imageIndex = 0;		
	}

	@Override
	protected void act(Graphics g) {
		// this is when we show the spaceship (paint on the canvas)
		setImage(frames.get(imageIndex).getImage());
		g.drawImage(getImage(), x, y, null);
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		// the user hits the up arrow
		if(key == KeyEvent.VK_UP)
			dy = -3;
		
		// the user hits the down arrow
		if(key == KeyEvent.VK_DOWN)
			dy = 3;
		
		// the user hits the left arrow
		if(key == KeyEvent.VK_LEFT)
			dx = -3;
		
		// the user hits the right arrow
		if(key == KeyEvent.VK_RIGHT)
			dx = 3;
	}

	public void keyReleased(KeyEvent e) {
		
		// we have to handle keyReleased events as well - otherwise the spaceship
    	// would not be able to stand still
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP)
			dy = 0;
		
		if(key == KeyEvent.VK_DOWN)
			dy = 0;
		
		if(key == KeyEvent.VK_LEFT)
			dx = 0;
		
		// the user hits the right arrow
		if(key == KeyEvent.VK_RIGHT)
			dx = 0;
	}
}
