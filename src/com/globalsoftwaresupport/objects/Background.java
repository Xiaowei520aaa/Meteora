package com.globalsoftwaresupport.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.globalsoftwaresupport.constants.Constants;
import com.globalsoftwaresupport.image.ImageFactory;
import com.globalsoftwaresupport.image.ImageType;

public class Background extends Sprite {
	
	private Rectangle firstRegion;
	private Rectangle secondRegion;
	private List<ImageIcon> frames;
	private int dy = 1;
	
	public Background(int x, int y) {
		this.x = x;
		this.y = y;
		initialize();
	}

	private void initialize() {
		frames = new ArrayList<>();
		frames.add(ImageFactory.createImage(ImageType.BACKGROUND));
		frames.add(ImageFactory.createImage(ImageType.BACKGROUND));
		
		setImage(frames.get(0).getImage());
		
		firstRegion = new Rectangle(0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		secondRegion = new Rectangle(0, -Constants.GAME_HEIGHT, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
	}
	
	private boolean isBoundReached() {
		return firstRegion.getY() > Constants.GAME_HEIGHT;
	}
	
	private void resetBounds() {
		firstRegion.setBounds(0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		secondRegion.setBounds(0, -Constants.GAME_HEIGHT, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
	}

	@Override
	protected void update() {
		firstRegion.y += dy;
		secondRegion.y += dy;
		
		if(isBoundReached())
			resetBounds();
	}

	@Override
	protected void act(Graphics g) {
		g.drawImage(frames.get(0).getImage(), firstRegion.x, firstRegion.y, null);
		g.drawImage(frames.get(1).getImage(), secondRegion.x, secondRegion.y, null);
	}
}
