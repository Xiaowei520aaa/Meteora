package com.globalsoftwaresupport.app;

import java.awt.EventQueue;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.globalsoftwaresupport.ui.GameMainFrame;

public class App {
	public static ResourceBundle messages;
	public static Locale currentLocale;
	static NumberFormat nf;

	public static void main(String[] args) {
		String language;
		String country;

		if (args.length != 2) {
			language = new String("en");
			country = new String("US");
		} else {
			language = new String(args[0]);
			country = new String(args[1]);
		}

		Locale currentLocale;
		currentLocale = new Locale(language, country);

		try {
			messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
			nf = NumberFormat.getInstance(currentLocale);
		} catch (MissingResourceException e) {
			System.err.println("\n\nNo langauge file located (no properties file)...\n\n");
		}
		// we make sure that the application will use the underlying OS related look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		// start the application on a distinct thread
		// so we run the application on the Event Dispatch Thread (EDT)
		EventQueue.invokeLater(() -> {
			new GameMainFrame();
		});	
	}
}
