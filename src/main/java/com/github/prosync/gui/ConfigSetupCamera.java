/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.gui;

import com.github.prosync.domain.Camera;
import com.github.prosync.logic.CameraController;
import com.github.prosync.logic.GUIServices;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;

import static javax.swing.Action.NAME;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author Rubenhag
 */
public class ConfigSetupCamera {

    public ConfigSetupCamera() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }
                JFrame frame = new JFrame("Hvilke kameraer vil du konfigurere?");
                frame.setPreferredSize(new Dimension(800, 600));
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new ConfigSetupCameraPane(frame));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    private class ConfigSetupCameraPane extends JPanel {

        ArrayList<Camera> cameras;
        ArrayList<String> nics;

        public ConfigSetupCameraPane(JFrame contentFrame) {
            final JFrame frame = contentFrame;
            cameras = new ArrayList<>();
            nics = new ArrayList<>();
            ArrayList<NetworkInterface> interfaces;
            try {
                interfaces = GUIServices.getConectedWIFINICS();
                if (interfaces.size() < 1) {
                    System.out.println("Lista er tom");
                }
                for (NetworkInterface anInterface : interfaces) {
                    nics.add(anInterface.getDisplayName());
                }
            } catch (SocketException ex) {
                Logger.getLogger(ConfigSetupCamera.class.getName()).log(Level.SEVERE, null, ex);
            }

            setSize(800, 600);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            //Placeholder
            cameras.add(new Camera("Kamera 1"));
            cameras.add(new Camera("Kamera 2"));
            cameras.add(new Camera("Kamera 3"));
            //nics.add("wlan0");

            JPanel panel;
            if (cameras.size() < 0) {
                for (Camera aCamera : cameras) {
                    final Camera camera = aCamera;
                    final JComboBox dropDown = new JComboBox(nics.toArray());
                    final JCheckBox checkBox = new JCheckBox(aCamera.getCamName());
                    final JTextField textField = new JTextField(20);

                    panel = new JPanel(new FlowLayout());

                    gbc.gridy++;

                    try {
                        aCamera.setNic(GUIServices.findInterface(dropDown.getSelectedItem().toString()));
                    } catch (SocketException e) {
                        System.out.println(cameras.size());
                        e.printStackTrace();
                    }

                    dropDown.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            try {
                                camera.setNic(GUIServices.findInterface(dropDown.getSelectedItem().toString()));
                            } catch (SocketException e) {
                                e.printStackTrace();
                            }
                        }

                    });

                    checkBox.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            camera.setSelected(checkBox.isSelected());
                        }
                    });

                    textField.getDocument().addDocumentListener(new DocumentListener() {

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
            } else {
                JOptionPane.showMessageDialog(this, "Ingen kameraer er koblet til");
            }
            gbc.gridy++;
            JButton submit = new JButton("Neste");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GUIServices.clearCameraList();
                    if (cameras.size() > 0) {
                        for (Camera aCamera : cameras) {
                            System.out.println("Nic: " + aCamera.getNic());
                            System.out.println("Selected: " + aCamera.getSelected());
                            System.out.println("Passord: " + aCamera.getPassword());
                            GUIServices.addCamera(aCamera.getCamName(), aCamera.getNic(), aCamera.getPassword());
                        }
                    }
                    new ConfigFinal();
                    frame.setVisible(false);

                }
            });
            add(submit, gbc);
        }

    }
}
