package com.github.prosync.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jim-espen on 10/17/14.
 */
public class ChooseCameraMenu extends JFrame {
	private JCheckBox kamera1CheckBox;
	private JCheckBox kamera2CheckBox;
	private JCheckBox kamera3CheckBox;
	private JButton tilbakeButton;
	private JButton hovedmenyButton;
	private JButton okButton;

	public ChooseCameraMenu(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new GridBagLayout());
		add(kamera1CheckBox);
		add(kamera2CheckBox);
		add(kamera3CheckBox);
		add(tilbakeButton);
		add(hovedmenyButton);
		add(okButton);
	}
}
