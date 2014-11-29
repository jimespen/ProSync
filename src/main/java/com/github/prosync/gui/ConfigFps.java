/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.gui;

import com.github.prosync.domain.Config;
import com.github.prosync.domain.Constants;
import com.github.prosync.logic.CameraController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractAction;
import static javax.swing.Action.NAME;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Rubenhag
 */
public class ConfigFps {
    CameraController cc = new CameraController();
    final Config config;

    public ConfigFps(Config modeConfig) {
        this.config = modeConfig;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }
                JFrame frame = new JFrame("Modus");
                frame.setPreferredSize(new Dimension(800,600));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new ResolutionPane(frame));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    
    public class ResolutionPane extends JPanel{
        
        public ResolutionPane(JFrame contentFrame){
                final JFrame frame = contentFrame;
                setSize(800,600);
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.anchor = GridBagConstraints.WEST;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                

            switch (config.getModeSelected()) {
                case Constants.VIDEO_MODE:
                    config.setResolutionValues(Constants.getVideoResolutions());
                    break;
                case Constants.PHOTO_MODE:
                    config.setResolutionValues(Constants.getPhotoResolutions());
                    break;
                case Constants.BURST_MODE:
                    config.setResolutionValues(Constants.photoResolutions);
                    break;
            }
                
                setBorder(new TitledBorder("Oppløsning"));
                ButtonGroup bg = new ButtonGroup();
                for (String value : config.getResolutionValues()) {
                    JRadioButton rb = new JRadioButton(new ResolutionAction(config, value));
                    bg.add(rb);
                    add(rb, gbc);
                }
                
                JButton submit = new JButton("Send til kamera");
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(config.getModeSelected());
                        System.out.println(config.getResolutionSelected());
                        frame.setVisible(false);
                    }
                });
                add(submit, gbc);
        }
                
    }
    
    
    public class ResolutionAction extends AbstractAction {

        private final Config mode;
        private final String value;

        public ResolutionAction(Config mode, String value) {
            this.mode = mode;
            this.value = value;
            putValue(NAME, value);
        }

        public Config getMode() {
            return mode;
        }
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            getMode().setResolutionSelected(value);
        }
    }

}
