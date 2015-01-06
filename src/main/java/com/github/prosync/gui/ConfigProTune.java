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
import java.util.ArrayList;
import java.util.Arrays;
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
public class ConfigProTune {

    final Config config = GUIServices.getConfig();

    public ConfigProTune() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }

                JFrame frame = new JFrame("ProTune");
                frame.setPreferredSize(new Dimension(800, 600));
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new ProTunePane(frame));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class ProTunePane extends JPanel {

        public ProTunePane(JFrame contentFrame) {
            final JFrame frame = contentFrame;
            setSize(800, 600);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            setBorder(new TitledBorder("ProTune"));
            ButtonGroup bg = new ButtonGroup();
            JRadioButton yes = new JRadioButton(new ProTuneAction(config, true));
            JRadioButton no = new JRadioButton(new ProTuneAction(config,false));
            yes.setText("JA");
            no.setText("NEI");
            bg.add(yes);
            bg.add(no);
            add(yes, gbc);
            add(no, gbc);
            JButton submit = new JButton("Neste");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(config.getProTuneSlected());
                    new ConfigFps(config);
                    frame.setVisible(false);
                }
            });
            add(submit, gbc);

        }
    }

    public class ProTuneAction extends AbstractAction {

        private final Config config;
        private final boolean value;

        public ProTuneAction(Config mode, boolean value) {
            this.config = mode;
            this.value = value;
        }

        public Config getConfig() {
            return config;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getConfig().setProTuneSelected(value);
        }
    }

}
