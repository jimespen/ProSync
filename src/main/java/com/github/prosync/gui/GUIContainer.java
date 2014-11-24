/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.prosync.gui;

import static com.github.prosync.gui.MainMenu.RIGHT_TO_LEFT;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Ruben
 */
public class GUIContainer {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    private Container menu;
    
    public void createMenu(){
        if (RIGHT_TO_LEFT) {
		menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		JButton button;
		//menu.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			//natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		button = new JButton("Konfigurer");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
    		menu.add(button, c);

		button = new JButton("Hent ut");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		menu.add(button, c);

		button = new JButton("Opptak");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		menu.add(button, c);
                
                navPane  panel = new navPane();
                
                c.gridx = 0;
                c.gridy = 1;
                menu.add(panel.createAndShowPane(), c);
    }
    
    
    public void createAndShowGUI(){
        JFrame frame = new JFrame("ProSync");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createMenu();
        frame.add(menu);
        
        frame.pack();
        frame.setVisible(true);
        
        
    }
    
}
