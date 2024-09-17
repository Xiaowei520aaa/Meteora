package com.globalsoftwaresupport.objects;

import java.awt.Graphics;
import java.awt.Image;

// sprites are the images that will be animated on the screen
public abstract class Sprite {
	
	// x and y coordinates
	protected int x;
	protected int y;

	// Images for every single sprite (object)
	protected Image image;
	
	// we have to track whether the given object is dead or not
	// Unity and LibGDX that handle objects efficiently
	protected boolean dead;
	
	// update() and paint()
	protected abstract void update();
	// paint the sprite onto the canvas
	protected abstract void act(Graphics g);
	
	public void update(Graphics g) {
		update();
		act(g);
	}
	
	public void die() {
		this.dead = true;
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
}
