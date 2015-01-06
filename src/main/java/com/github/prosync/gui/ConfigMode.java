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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Rubenhag
 */
public class ConfigMode {

    CameraController cc = new CameraController();

    public ConfigMode() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }

                JFrame frame = new JFrame("Modus");
                frame.setPreferredSize(new Dimension(800, 600));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new ModePane(frame));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class ModePane extends JPanel {

        public ModePane(JFrame contentFrame) {
            final JFrame frame = contentFrame;
            setSize(800, 600);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            ArrayList<String> modeValues = new ArrayList<>(Arrays.asList(Constants.VIDEO_MODE, Constants.PHOTO_MODE, Constants.BURST_MODE));
            final Config config = new Config();
            config.setModeValues(modeValues);
            setBorder(new TitledBorder("Modus"));
            ButtonGroup bg = new ButtonGroup();
            for (String value : modeValues) {
                if (modeValues.indexOf(value) == 0) {
                }
                JRadioButton rb = new JRadioButton(new ModeAction(config, value));
                bg.add(rb);
                add(rb, gbc);
            }

            JButton submit = new JButton("Send til kamera");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (config.getModeSelected().equals(Constants.VIDEO_MODE)) {
                            //cc.setModeToVideo();
                        } else if (config.getModeSelected().equals(Constants.PHOTO_MODE)) {
                            //cc.setModeToPhoto();
                        } else if (config.getModeSelected().equals(Constants.BURST_MODE)) {
                            //cc.setModeToBurst();
                        }
                        frame.setVisible(false);
                        new ConfigResolution(config);
                    } catch (NullPointerException npe) {
                        npe.printStackTrace(System.err);
                        JOptionPane.showMessageDialog(frame, "Du må velge en modus", "Error!", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
            add(submit, gbc);

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
