package com.globalsoftwaresupport.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.globalsoftwaresupport.callbacks.GameEventListener;
import com.globalsoftwaresupport.constants.Constants;
import com.globalsoftwaresupport.constants.GameVariables;
import com.globalsoftwaresupport.objects.Background;
import com.globalsoftwaresupport.objects.Laser;
import com.globalsoftwaresupport.objects.Meteor;
import com.globalsoftwaresupport.objects.SpaceShip;
import com.globalsoftwaresupport.random.RandomGenerator;
import com.globalsoftwaresupport.app.App;

public class GamePanel extends JPanel {
	
	private Timer timer;
	private SpaceShip spaceShip;
	private Background background;
	private List<Laser> lasers;
	private List<Meteor> meteors;
	private RandomGenerator randomGenerator;
	private CollisionDetector collisionDetector;

	public GamePanel() {
		initalizeVariables();
		initializeLayout();
		startAnimation();
	}

	private void initalizeVariables() {
		addKeyListener(new GameEventListener(this));
		spaceShip = new SpaceShip();
		background = new Background(0, 0);
		lasers = new ArrayList<>();
		meteors = new ArrayList<>();
		randomGenerator = new RandomGenerator();
		collisionDetector = new CollisionDetector();
	}

	private void startAnimation() {
		// we want to update + repaint the canvas in every N seconds
		// how many ms to wait
		timer = new Timer(Constants.GAME_SPEED, new GameLoop(this));
		timer.start();
	}

	private void initializeLayout() {
		setPreferredSize(new Dimension(Constants.GAME_WIDTH, Constants.GAME_HEIGHT));
		setFocusable(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		handleCanvas(g);
	}

	private void handleCanvas(Graphics g) {
		
		if(GameVariables.IN_GAME) {
			// this is the 'in game' situation
			handleBackground(g);
			handleSpaceShip(g);	
			handleLaserBeams(g);
			handleMeteors(g);
			handleScoreAndShields(g);
		} else {
			// this is the game over case
			if(timer.isRunning())
				timer.stop();
			
			gameOver(g);
		}
		
		// we want to synch the canvas
		Toolkit.getDefaultToolkit().sync();
	}

	private void handleScoreAndShields(Graphics g) {
		
		if(!GameVariables.IN_GAME) {
			return;
		}
		
		Font font = new Font(App.messages.getString("font"), Font.BOLD, 20);
		g.setColor(Color.GRAY);
		g.setFont(font);
		g.drawString(App.messages.getString("score") + " " + GameVariables.SCORE, Constants.GAME_WIDTH - 150, 50);
		g.drawString(App.messages.getString("shields") + " " + GameVariables.SHIELDS, 50, 50);
	}

	private void gameOver(Graphics g) {
		
		// want to move the background
		background.update(g);
		
		// GAME OVER !!!
		Font font = new Font(App.messages.getString("font"), Font.BOLD, 50);
		FontMetrics fontMetrics = getFontMetrics(font);
		
		// draw game over
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(Constants.GAME_OVER, Constants.GAME_WIDTH/2 
				-  fontMetrics.stringWidth(Constants.GAME_OVER)/2
				, Constants.GAME_HEIGHT/2-100);
		
		// draw the score
		g.setColor(Color.YELLOW);
		g.drawString(App.messages.getString("score") + " " + GameVariables.SCORE, Constants.GAME_WIDTH/2 
				- fontMetrics.stringWidth("Score: " + GameVariables.SCORE)/2
				, Constants.GAME_HEIGHT-300);

		// // quit the game
		// g.setColor(Color.GREEN);
		// g.setFont(font);
		// // 计算按钮的位置   
		// int buttonWidth = Constants.CLOSE_GAME_BUTTON.getPreferredSize().width;  
		// int buttonHeight = Constants.CLOSE_GAME_BUTTON.getPreferredSize().height;  
		// int buttonX = (Constants.GAME_WIDTH - buttonWidth) / 2;  
		// int buttonY = (Constants.GAME_HEIGHT - buttonHeight) / 2;  
		// Constants.CLOSE_GAME_BUTTON.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);  
		// add(Constants.CLOSE_GAME_BUTTON);
		// // Create the "Game Over" button  
    	// Constants.CLOSE_GAME_BUTTON.addActionListener(e -> {  
		// int choice = JOptionPane.showConfirmDialog(this, "What would you like to do?", "Game Over", JOptionPane.YES_NO_OPTION);  
		// if (choice == JOptionPane.YES_OPTION) {  
		// 	// Restart the game  
		// 	remove(Constants.CLOSE_GAME_BUTTON);
		// 	restartGame();  
		// } else {  
		// 	// Close the game  
		// 	System.exit(0);  
		// }  
		// });  
	}

	private void handleMeteors(Graphics g) {
		for(Meteor meteor : meteors)
			meteor.update(g);
	}

	private void handleLaserBeams(Graphics g) {
		for(Laser laser : lasers)
			if(!laser.isDead())
				laser.update(g);		
	}

	private void handleBackground(Graphics g) {
		background.update(g);
	}

	private void handleSpaceShip(Graphics g) {
		spaceShip.update(g);
	}

	public void loop() {
		// update
		//update();
		// repaint the canvas
		if (!GameVariables.PAUSED) {  
            update();  
			repaint();
        }  
	}
	
	private void update() {
		
		if(spaceShip.isDead()) {
			GameVariables.IN_GAME = false;
			return;
		}
		
		// detect collisions
		// check whether the game is over
		// generate the meteors at random
		if(randomGenerator.isMeteorGenerated()) {
			int randomX = randomGenerator.generateRandomX();
			int randomY = 0 - Constants.METEOR_HEIGHT;
			meteors.add(new Meteor(randomX, randomY));
		}
		
		// meteors reach the bottom of the canvas
		// if one of the meteors reaches the bottom of the screen 
		// it is game over (space ship dies automatically)
		for(Meteor meteor : meteors)
			if(meteor.isDead())
				spaceShip.die();		
		
		
		// check whether to remove dead laser beams
		List<Laser> destroyedLasers = new ArrayList<>();
		
		for(Laser laser : lasers)
			if(laser.isDead())
				destroyedLasers.add(laser);
		
		lasers.removeAll(destroyedLasers);
				
		
		// check the collisions
		// collision between the lasers and the meteors
		Meteor destroyedMeteor = null;
		Laser destroyedLaser = null;
		
		// brute-force approach because we have to consider all the meteor-laser pairs
		// O(M*N) M: number of meteors N: number of lasers
		for(Laser laser : lasers) {
			if(!laser.isDead()) {			
				// all the meteors (brute-force approach)
				for(Meteor meteor : meteors) {
					if(collisionDetector.collisionLaserMeteor(laser, meteor)) {
						destroyedMeteor = meteor;
						destroyedLaser = laser;
						GameVariables.SCORE += 20;
					}
				}
				
				meteors.remove(destroyedMeteor);
			}
		}
		
		lasers.remove(destroyedLaser);
		
		// detect the collisions between meteors and the spaceship
		destroyedMeteor = null;
		
		for(Meteor meteor : meteors) {
			if(collisionDetector.collisionMeteorSpaceShip(meteor, spaceShip)) {
				destroyedMeteor = meteor;
				GameVariables.SHIELDS--;
						
				if(GameVariables.SHIELDS < 0)
					spaceShip.die();
			}
		}
		
		meteors.remove(destroyedMeteor);
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();  
        if (key == KeyEvent.VK_Q) {  
            System.exit(0); // Quit the game when 'Q' is pressed  
        } else if (key == KeyEvent.VK_P) {  
            GameVariables.PAUSED = !GameVariables.PAUSED; // Toggle the pause state when 'P' is pressed  
        } else { 
			spaceShip.keyPressed(e);
			
			// when the user hits the 'space' this is when the
			// laser beams are generated
			key = e.getKeyCode();
			
			if(key == KeyEvent.VK_SPACE) {
				if(GameVariables.IN_GAME) {
					int x = spaceShip.getX();
					int y = spaceShip.getY();
					
					lasers.add(new Laser(x, y));
				}	
			}
		}	
	} 

	public void keyReleased(KeyEvent e) {
		spaceShip.keyReleased(e);
	}

	// public void restartGame() {  
    //     initalizeVariables(); // Reset the game variables  
    //     GameVariables.IN_GAME = true; // Set the game state to in-game  
    //     if (!timer.isRunning()) {  
    //         timer.start(); // Start the game loop if it's not running  
    //     }  
    //     repaint(); // Repaint the canvas to display the reset game state  
    // }
}
