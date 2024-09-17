package com.globalsoftwaresupport.objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.globalsoftwaresupport.constants.Constants;

public class Meteor extends Sprite {

	private List<ImageIcon> frames;
	private int imageIndex;
	
	public Meteor(int x, int y) {
		this.x = x;
		this.y = y;
		initialize();	
	}
	
	private void initialize() {
		frames = new ArrayList<>();
		frames.add(new ImageIcon("resources/images/meteor_01.png"));
		frames.add(new ImageIcon("resources/images/meteor_02.png"));
		frames.add(new ImageIcon("resources/images/meteor_03.png"));
		frames.add(new ImageIcon("resources/images/meteor_04.png"));
		frames.add(new ImageIcon("resources/images/meteor_05.png"));
		frames.add(new ImageIcon("resources/images/meteor_06.png"));
		frames.add(new ImageIcon("resources/images/meteor_07.png"));
		frames.add(new ImageIcon("resources/images/meteor_08.png"));
		frames.add(new ImageIcon("resources/images/meteor_09.png"));
		frames.add(new ImageIcon("resources/images/meteor_10.png"));
		setImage(frames.get(0).getImage());
	}

	@Override
	protected void update() {
		
		// we have to move the meteor from top to bottom
		// increment the y position
		y += Constants.METEOR_SPEED;
		
		// if the meteor is outside the scope of the canvas we have
		// to remove the meteor
		// THIS IS THE END OF THE GAME !!!
		if(y >= Constants.GAME_HEIGHT)
			die();
			
		// we have to update the index of the images
		imageIndex++;
		
		if(imageIndex > frames.size()-1)
			imageIndex = 0;
	}

	@Override
	protected void act(Graphics g) {
		g.drawImage(frames.get(imageIndex).getImage(), x, y, null);
	}
}
