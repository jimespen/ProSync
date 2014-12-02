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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ruben
 */
public class ConfigFrame {
    ArrayList<JComponent> paneList = new ArrayList<>();
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
                ModePane modePane = new ModePane(conf);
                ProTunePane proTunePane = new ProTunePane(frame,conf);
                paneList.add(modePane);
                paneList.add(proTunePane);
                frame.setPreferredSize(new Dimension(800, 600));
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setLayout(new FlowLayout());
                JButton next = new JButton("Neste");
                next.setActionCommand("next");
                System.out.println(paneList.size());
                System.out.println(paneList.indexOf(proTunePane));
                next.addActionListener(new ActionListener(){
                        
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        System.out.println("DETTE ER EN TEST:::********"+ae.toString());
                        JButton btn = (JButton)ae.getSource();
                        switch(btn.getActionCommand()){
                            case "next":
                                nextPane(frame);
                                
                                break;
                        }
                    }
                });
                
                frame.add(paneList.get(0));
                frame.add(next);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                for(Component aComponent : frame.getComponents()){
                    System.out.println(aComponent.toString());
                }
            }
        });
    }
    
    public void nextPane(JFrame frame){
        System.out.println(i);
        frame.remove(paneList.get(i));
        frame.add(paneList.get(i++));
    }
}
