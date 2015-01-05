package com.github.prosync.gui;

/**
 * Created by oystein on 04.01.2015.
 */

import com.github.prosync.domain.Camera;
import com.github.prosync.logic.CameraController;
import com.github.prosync.logic.GUIServices;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class CaptureMode extends JPanel {
    private GUIServices guiS;

    public CaptureMode(final GUIServices guiS) {
        this.guiS = guiS;
        setupCameras();
    }

    private void setupCameras(){

        ArrayList<String> interfaces;

        try {
            interfaces = guiS.getConectedWIFINames();
            if (interfaces.size() < 1) {
                System.out.println("Lista er tom");
            }
        } catch (SocketException ex) {
            Logger.getLogger(ConfigSetupCamera.class.getName()).log(Level.SEVERE, null, ex);
        }

        setSize(1000, 600);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        System.out.println("GBCX " + gbc.gridx);


        try {
            final JComboBox dropDown1 = new JComboBox(guiS.getConectedWIFINames().toArray());
            dropDown1.setPreferredSize(new Dimension(450, 20));

            final JCheckBox checkBox1 = new JCheckBox("Kamera 1");
            final JTextField textField1 = new JTextField(20);
            final JPanel panel1 = new JPanel(new FlowLayout());

            panel1.add(checkBox1);
            panel1.add(dropDown1);
            panel1.add(textField1);

            add(panel1, gbc);
            gbc.gridy++;

            final JComboBox dropDown2 = new JComboBox(guiS.getConectedWIFINames().toArray());
            dropDown2.setPreferredSize(new Dimension(450, 20));

            final JCheckBox checkBox2 = new JCheckBox("Kamera 2");
            final JTextField textField2 = new JTextField(20);
            final JPanel panel2 = new JPanel(new FlowLayout());

            panel2.add(checkBox2);
            panel2.add(dropDown2);
            panel2.add(textField2);
            add(panel2, gbc);

            gbc.gridy++;


            final JComboBox dropDown3 = new JComboBox(guiS.getConectedWIFINames().toArray());
            dropDown3.setPreferredSize(new Dimension(450, 20));

            final JCheckBox checkBox3 = new JCheckBox("Kamera 3");
            final JTextField textField3 = new JTextField(20);
            final JPanel panel3 = new JPanel(new FlowLayout());

            panel3.add(checkBox3);
            panel3.add(dropDown3);
            panel3.add(textField3);
            add(panel3, gbc);

            gbc.gridy++;

            final JButton startRecording = new JButton("Start");
            final JButton stopRecording = new JButton("Stop");
            stopRecording.setEnabled(false);

            final CaptureMode cm = this;
            startRecording.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        guiS.clearCameraList();
                        if (checkBox1.isSelected())
                            guiS.addCamera("camera1", guiS.findInterface(dropDown1.getSelectedItem().toString()), textField1.getText());

                        if (checkBox2.isSelected())
                            guiS.addCamera("camera2", guiS.findInterface(dropDown2.getSelectedItem().toString()), textField2.getText());

                        if (checkBox3.isSelected())
                            guiS.addCamera("camera3", guiS.findInterface(dropDown3.getSelectedItem().toString()), textField3.getText());

                    } catch (SocketException e1) {
                        e1.printStackTrace();
                    }

                    guiS.startShutter();

                    checkBox1.setEnabled(false);
                    checkBox2.setEnabled(false);
                    checkBox3.setEnabled(false);

                    textField1.setEnabled(false);
                    textField2.setEnabled(false);
                    textField3.setEnabled(false);

                    dropDown1.setEnabled(false);
                    dropDown2.setEnabled(false);
                    dropDown3.setEnabled(false);

                    startRecording.setEnabled(false);
                    stopRecording.setEnabled(true);
                }
            });
            add(startRecording, gbc);

            gbc.gridy++;

            stopRecording.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        guiS.clearCameraList();
                        if (checkBox1.isSelected())
                            guiS.addCamera("camera1", guiS.findInterface(dropDown1.getSelectedItem().toString()), textField1.getText());

                        if (checkBox2.isSelected())
                            guiS.addCamera("camera2", guiS.findInterface(dropDown2.getSelectedItem().toString()), textField2.getText());

                        if (checkBox3.isSelected())
                            guiS.addCamera("camera3", guiS.findInterface(dropDown3.getSelectedItem().toString()), textField3.getText());

                    } catch (SocketException e1) {
                        e1.printStackTrace();
                    }

                    checkBox1.setEnabled(true);
                    checkBox2.setEnabled(true);
                    checkBox3.setEnabled(true);

                    textField1.setEnabled(true);
                    textField2.setEnabled(true);
                    textField3.setEnabled(true);

                    dropDown1.setEnabled(true);
                    dropDown2.setEnabled(true);
                    dropDown3.setEnabled(true);


                    guiS.stopShutter();
                    startRecording.setEnabled(true);
                    stopRecording.setEnabled(false);
                }
            });
            add(stopRecording, gbc);


        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private void disableInterface(){

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Kofigurer");
                frame.setPreferredSize(new Dimension(1000, 600));
                JTabbedPane config = new JTabbedPane();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                config.add(new CaptureMode(new GUIServices()));
                frame.add(config);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}

