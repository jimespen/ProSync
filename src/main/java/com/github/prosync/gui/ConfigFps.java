/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.gui;

import com.github.prosync.domain.Config;
import com.github.prosync.domain.Constants;
import com.github.prosync.logic.GUIServices;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
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
public class ConfigFps {

    final Config config;

    public ConfigFps() {
        this.config = GUIServices.getConfig();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }
                JFrame frame = new JFrame("Frames per sekund");
                frame.setPreferredSize(new Dimension(800, 600));
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new FPSPane(frame));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    private class FPSPane extends JPanel {

        public FPSPane(JFrame contentFrame) {
            final JFrame frame = contentFrame;
            setSize(800, 600);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            config.setFpsValues(Constants.getFpsList(config.getResolutionSelected()));
            
            setBorder(new TitledBorder("FPS"));
            ButtonGroup bg = new ButtonGroup();
            for (String value : config.getFpsValues()) {
                JRadioButton rb = new JRadioButton(new FPSAction(config, value));
                bg.add(rb);
                add(rb, gbc);
            }

            JButton submit = new JButton("Neste");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(config.getModeSelected());
                    System.out.println(config.getResolutionSelected());
                    System.out.println(config.getFpsSelected());
                    new ConfigSetupCamera();
                    frame.setVisible(false);
                }
            });
            add(submit, gbc);
        }

    }

    private class FPSAction extends AbstractAction {

        private final Config mode;
        private final String value;

        public FPSAction(Config mode, String value) {
            this.mode = mode;
            this.value = value;
            putValue(NAME, value);
        }

        public Config getMode() {
            return mode;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getMode().setFpsSelected(value);
        }
    }

}
