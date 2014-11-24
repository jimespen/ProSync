/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Rubenhag
 */
public class ConfigSetupCamera extends JPanel {
    
    ArrayList<Camera> cameras;
    ArrayList<String> nics;

    public ConfigSetupCamera() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }
                cameras = new ArrayList<>();
                nics = new ArrayList<>();
                setSize(800,600);
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.anchor = GridBagConstraints.WEST;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                
                //Placeholder
                cameras.add(new Camera("Kamera 1", "Kamera 1"));
                cameras.add(new Camera("Kamera 2", "Kamera 2"));
                cameras.add(new Camera("Kamera 3", "Kamera 3"));
                nics.add("wlan15");
                nics.add("wlan0");
                nics.add("wlanWin");
                
                
                JCheckBox checkBox;
                JComboBox dropDown;
                JPanel panel;
                JTextField textField;
                for(Camera aCamera : cameras){
                    gbc.gridy++;
                    textField = new JTextField(20);
                    panel = new JPanel(new FlowLayout());
                    dropDown  = new JComboBox(nics.toArray());
                    checkBox = new JCheckBox(aCamera.getCamName());
                    panel.add(checkBox);
                    panel.add(dropDown);
                    panel.add(textField);
                    add(panel, gbc);
                    
                }
                
            }
        });
    }

    public class Camera {

        private String nic;
        private String camName;

        public Camera(String nic, String camName) {
            this.nic = nic;
            this.camName = camName;
        }

        public String getNic(){
            return nic;
        }

        public String getCamName(){
            return camName;
        }

    }

    public class ModeAction extends AbstractAction {

        private final Camera camera;
        private final String value;

        public ModeAction(Camera mode, String value) {
            this.camera = mode;
            this.value = value;
            putValue(NAME, value);
        }

        public Camera getCamera() {
            return camera;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
}
