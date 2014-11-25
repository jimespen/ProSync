/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.gui;

import com.github.prosync.domain.Camera;
import com.github.prosync.logic.CameraController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Rubenhag
 */
public class ConfigSetupCamera extends JPanel {
    
    ArrayList<Camera> cameras;
    ArrayList<String> nics;
    CameraController cc = new CameraController();

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
                nics.add("wlan0");
                nics.add("wlan1");
                nics.add("wlan2");
                
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
                
                JPanel panel;

                for(Camera aCamera : cameras){
                    final Camera camera = aCamera;
                    final JComboBox dropDown  = new JComboBox(nics.toArray());
                    final JCheckBox checkBox = new JCheckBox(aCamera.getCamName());
                    final JTextField textField = new JTextField(20);
                    
                    panel = new JPanel(new FlowLayout());
                    
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
                    
                    textField.getDocument().addDocumentListener(new DocumentListener(){

                        @Override
                        public void insertUpdate(DocumentEvent de) {
                            camera.setPassword(textField.getText());
                        }

                        @Override
                        public void removeUpdate(DocumentEvent de) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void changedUpdate(DocumentEvent de) {
                            
                        }
                    });
                    
                    aCamera = camera;
                    
                    panel.add(checkBox);
                    panel.add(dropDown);
                    panel.add(textField);
                    add(panel, gbc);
                    
                }
                gbc.gridy++;
                JButton submit = new JButton("Send til kamera");
                submit.setSize(new Dimension(14,14));
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(Camera aCamera : cameras){
                            System.out.println("Nic: "+aCamera.getNic());
                            System.out.println("Selected: "+aCamera.getSelected());
                            System.out.println("Passord: "+aCamera.getPassword());
                        }
                        
                    }
                });
                add(submit, gbc);
                
            }
        });
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
}
