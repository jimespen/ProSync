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
                cameras.add(new Camera("Kamera 1"));
                cameras.add(new Camera("Kamera 2"));
                cameras.add(new Camera("Kamera 3"));
                nics.add("wlan15");
                nics.add("wlan0");
                nics.add("wlanWin");
                
                

                JPanel panel;
                JTextField textField;
                for(Camera aCamera : cameras){
                    final Camera camera = aCamera;
                    final JComboBox dropDown  = new JComboBox(nics.toArray());
                    final JCheckBox checkBox = new JCheckBox(aCamera.getCamName());
                    
                    
                    panel = new JPanel(new FlowLayout());
                    textField = new JTextField(20);
                    
                    gbc.gridy++;
                    
                    aCamera.setNic(dropDown.getSelectedItem().toString());
                    
                    dropDown.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            camera.setNic(dropDown.getSelectedItem().toString());
                        }
                    
                    });
                    
                    checkBox.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            camera.setSelected(checkBox.isSelected());
                        }
                    });
                    
                    aCamera = camera;
                    
                    panel.add(checkBox);
                    panel.add(dropDown);
                    panel.add(textField);
                    add(panel, gbc);
                    
                }
                
                JButton submit = new JButton("Send til kamera");
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(Camera aCamera : cameras){
                            System.out.println("Nic: "+aCamera.getNic());
                            System.out.println("Selected: "+aCamera.getSelected());
                        }
                        
                    }
                });
                add(submit, gbc);
                
            }
        });
    }

    public class Camera {

        private String nic;
        private String camName;
        private boolean selected;

        public Camera(String camName) {
            this.camName = camName;
        }
        
        public void setNic(String nic){
            this.nic = nic;
        }

        public String getNic(){
            return nic;
        }

        public String getCamName(){
            return camName;
        }
        
        public void setSelected(boolean selected){
            this.selected = selected;
        }
        
        public boolean getSelected(){
            return selected;
        }

    }

    public class ModeAction extends AbstractAction {

        private final Camera camera;
        private final String nic;

        public ModeAction(Camera camera, String nic) {
            this.camera = camera;
            this.nic = nic;
            putValue(NAME, nic);
        }

        public Camera getCamera() {
            return camera;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            camera.setNic(nic);
        }
    }
    
    public class DropDownMenu extends JComboBox{
        
        Camera camera;
        
        public DropDownMenu(Camera camera){
            this.camera = camera;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            
        }
        
    }
}
