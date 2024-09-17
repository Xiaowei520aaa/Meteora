package com.globalsoftwaresupport.image;

import javax.swing.ImageIcon;

import com.globalsoftwaresupport.constants.Constants;

public class ImageFactory {

	public static ImageIcon createImage(ImageType imageType) {
		
		ImageIcon imageIcon = null;
		
		switch(imageType) {
			case LOGO:
				imageIcon = new ImageIcon(Constants.LOGO_URL);
				break;
			case BACKGROUND:
				imageIcon = new ImageIcon(Constants.BACKGROUND_URL);
				break;
			case LASER:
				imageIcon = new ImageIcon(Constants.LASER_URL);
				break;
		}
		
		return imageIcon;
	}
}
