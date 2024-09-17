package com.globalsoftwaresupport.ui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.globalsoftwaresupport.constants.Constants;
import com.globalsoftwaresupport.image.ImageFactory;
import com.globalsoftwaresupport.image.ImageType;

public class GameMainFrame extends JFrame {
	
	public GameMainFrame() {
		initialize();
	}

	private void initialize() {
		
		// we can add the panels to the main frame
		add(new GamePanel());
		
		pack();
		
		// define the title of the frame ...
		setTitle(Constants.GAME_TITLE);
		// set icon of the game (logo)
		setIconImage(ImageFactory.createImage(ImageType.LOGO).getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		// this is how we make the application (and the frame) visible
		setVisible(true);
	}
}
