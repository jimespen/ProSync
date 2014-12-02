/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.prosync.gui;

import com.github.prosync.domain.Config;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ruben
 */
public class ConfigFrame {
    ArrayList<JPanel> paneList = new ArrayList<>();
    JPanel panelSelected;
    ModePane modePane;
    ProTunePane proTunePane;
    
    int i = 0;
    public ConfigFrame(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }
                final JFrame frame = new JFrame("Config");
                Config conf = new Config();
                modePane = new ModePane(conf);
                proTunePane = new ProTunePane(frame,conf);
                
                
                paneList.add(modePane);
                paneList.add(proTunePane);
                frame.setPreferredSize(new Dimension(800, 600));
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                frame.add(paneList.get(0));
                panelSelected = paneList.get(0);
                gbc.gridx = 1; 
                addNext(frame, gbc);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                for(Component aComponent : frame.getComponents()){
                    System.out.println(aComponent.toString());
                }
            }
        });
    }
    
    public void nextPane(JFrame frame, GridBagConstraints gbc){
        gbc.gridx = 0;
        if(panelSelected instanceof ModePane){
            frame.remove(modePane);
            frame.add(proTunePane);
            panelSelected = proTunePane;
            frame.revalidate();
        }
    }
    
    public void addNext(JFrame frame, GridBagConstraints gbc){
        final GridBagConstraints c = gbc;
        gbc.gridx = 1;
        final JFrame frameUsed = frame;
        JButton next = new JButton("Neste");
        next.setActionCommand("next");
        next.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                JButton btn = (JButton)ae.getSource();
                        switch(btn.getActionCommand()){
                            case "next":
                                nextPane(frameUsed, c);
                                break;
                        }
            }
        });
        frame.add(next);
    }
}
