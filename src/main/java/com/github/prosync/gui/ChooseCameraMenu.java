package com.github.prosync.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jim-espen on 10/17/14.
 */
public class ChooseCameraMenu extends JFrame {
	private JCheckBox camera1CheckBox;
	private JCheckBox camera2CheckBox;
	private JCheckBox camera3CheckBox;
	private JButton backButton;
	private JButton mainMenuButton;
	private JButton okButton;

	public ChooseCameraMenu(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new GridBagLayout());
		add(camera1CheckBox);
		add(camera2CheckBox);
		add(camera3CheckBox);
		add(backButton);
		add(mainMenuButton);
		add(okButton);
	}
}
