package com.github.prosync.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jim-espen on 10/17/14.
 */
public class navPane extends JPanel {
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;

	public static void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		JButton button;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			//natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		button = new JButton("Tilbake");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Hovedmeny");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Neste");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(button, c);
	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
     * @return 
	 */
	public JPanel createAndShowPane() {
		//Create and set up the window.
		JPanel panel = new JPanel();

		//Set up the content pane.
		addComponentsToPane(panel.getRootPane());

		//Display the window.
		//panel.updateUI();
		//panel.setVisible(true);
                
                return panel;
                
                
                
	}
}
