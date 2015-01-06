/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.prosync.gui;

import com.github.prosync.domain.Config;
import com.github.prosync.domain.Constants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.AbstractAction;
import static javax.swing.Action.NAME;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Ruben
 */
public class ModePane extends JPanel {

        public ModePane(Config conf) {
            final Config config = conf;
            //setSize(800, 600);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            ArrayList<String> modeValues = new ArrayList<>(Arrays.asList(Constants.VIDEO_MODE, Constants.PHOTO_MODE, Constants.BURST_MODE));
            
            config.setModeValues(modeValues);
            setBorder(new TitledBorder("Modus"));
            ButtonGroup bg = new ButtonGroup();
            for (String value : modeValues) {
                if(modeValues.indexOf(value)==0){
                }
                JRadioButton rb = new JRadioButton(new ModeAction(config, value));
                bg.add(rb);
                add(rb, gbc);
            }
        }
        
        public class ModeAction extends AbstractAction {

        private final Config mode;
        private final String value;

        public ModeAction(Config mode, String value) {
            this.mode = mode;
            this.value = value;
            putValue(NAME, value);
        }

        public Config getMode() {
            return mode;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getMode().setModeSelected(value);
        }
    }
    }

    