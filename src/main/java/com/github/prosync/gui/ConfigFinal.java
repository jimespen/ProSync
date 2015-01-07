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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

/**
 * @author Ruben
 */
public class ConfigFinal {

    final Config config;

    public ConfigFinal() {
        this.config = GUIServices.getConfig();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }
                JFrame frame = new JFrame("Send config");
                frame.setPreferredSize(new Dimension(800, 600));
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new ConfigFinal.FinalPane(frame));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    private class FinalPane extends JPanel {

        public FinalPane(JFrame contentFrame) {
            final JFrame frame = contentFrame;
            setSize(800, 600);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            config.setFpsValues(Constants.getFpsList(config.getResolutionSelected()));

            setBorder(new TitledBorder("Final"));
            ButtonGroup bg = new ButtonGroup();
            JTextArea configSelected = new JTextArea();
            configSelected.append("Modus: " + config.getModeSelected());
            configSelected.append("\nOppl�sning: " + config.getResolutionSelected());
            configSelected.append("\nFPS: " + config.getFpsSelected());
            configSelected.append("\nProTune: " + config.getProTuneSlected());
            configSelected.append("\nSende til kamera?");
            add(configSelected);
            JButton ja = new JButton("Ja");
            ja.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GUIServices.sendConfigToAllConnectedCameras();
                    frame.setVisible(false);
                }
            });
            JButton nei = new JButton("Nei");
            nei.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            });
            add(ja);
            add(nei);
        }

    }
}
