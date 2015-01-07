/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.gui;

import com.github.prosync.domain.Config;
import com.github.prosync.domain.Constants;
import com.github.prosync.logic.CameraController;
import com.github.prosync.logic.GUIServices;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import static javax.swing.Action.NAME;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
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
public class ConfigResolution {
    final Config config = GUIServices.getConfig();
    public ConfigResolution() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }
                JFrame frame = new JFrame("Oppl�sning");
                frame.setPreferredSize(new Dimension(800,600));
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new ResolutionPane(frame));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    
    private class ResolutionPane extends JPanel{
        
        public ResolutionPane(JFrame contentFrame){
                final JFrame frame = contentFrame;
                setSize(800,600);
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.anchor = GridBagConstraints.WEST;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                

                if(config.getModeSelected().equals(Constants.VIDEO_MODE)){
                    config.setResolutionValues(Constants.getVideoResolutions());
                } else if(config.getModeSelected().equals(Constants.PHOTO_MODE)){
                    config.setResolutionValues(Constants.getPhotoResolutions());
                } else if(config.getModeSelected().equals(Constants.BURST_MODE)){
                    config.setResolutionValues(Constants.getPhotoResolutions());
                }
                
                setBorder(new TitledBorder("Oppl�sning"));
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
                        new ConfigProTune();
                        frame.setVisible(false);
                    }
                });
                add(submit, gbc);
        }
                
    }
    
    
    private class ResolutionAction extends AbstractAction {

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
