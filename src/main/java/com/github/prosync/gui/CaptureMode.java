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

public class CaptureMode {

    public CaptureMode() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }

                JFrame frame = new JFrame("Opptak");
                frame.setPreferredSize(new Dimension(800, 600));
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new CaptureMode.CapturePane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }


    private class CapturePane extends JPanel {

        GUIServices guiS;

        JCheckBox checkBox1;
        JCheckBox checkBox2;
        JCheckBox checkBox3;

        JTextField textField1;
        JTextField textField2;
        JTextField textField3;

        JComboBox dropDown1;
        JComboBox dropDown2;
        JComboBox dropDown3;

        JPanel panel1;
        JPanel panel2;
        JPanel panel3;

        JButton startRecording;
        JButton stopRecording;

        GridBagConstraints gbc;

        public CapturePane() {

            setupCameras();
        }

        private void setupCameras() {

            ArrayList<String> interfaces;

            try {
                interfaces = GUIServices.getConectedWIFINames();
                if (interfaces.size() < 1) {
                    System.out.println("Lista er tom");
                }
            } catch (SocketException ex) {
                Logger.getLogger(ConfigSetupCamera.class.getName()).log(Level.SEVERE, null, ex);
            }

            setSize(1000, 600);
            setLayout(new GridBagLayout());
            gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            System.out.println("GBCX " + gbc.gridx);


            try {
                addElements();
                addActionListners();

            } catch (SocketException e) {
                e.printStackTrace();
            }
        }

        private void addActionListners() {
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
                    disableInterface();
                }
            });
            stopRecording.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guiS.stopShutter();
                    enableInterface();
                }
            });
        }

        private void addElements() throws SocketException {
            dropDown1 = new JComboBox(guiS.getConectedWIFINames().toArray());
            dropDown1.setPreferredSize(new Dimension(450, 20));

            checkBox1 = new JCheckBox("Kamera 1");
            textField1 = new JTextField(20);
            panel1 = new JPanel(new FlowLayout());

            panel1.add(checkBox1);
            panel1.add(dropDown1);
            panel1.add(textField1);

            add(panel1, gbc);
            gbc.gridy++;

            dropDown2 = new JComboBox(guiS.getConectedWIFINames().toArray());
            dropDown2.setPreferredSize(new Dimension(450, 20));

            checkBox2 = new JCheckBox("Kamera 2");
            textField2 = new JTextField(20);

            panel2 = new JPanel(new FlowLayout());

            panel2.add(checkBox2);
            panel2.add(dropDown2);
            panel2.add(textField2);
            add(panel2, gbc);

            gbc.gridy++;


            dropDown3 = new JComboBox(guiS.getConectedWIFINames().toArray());
            dropDown3.setPreferredSize(new Dimension(450, 20));

            checkBox3 = new JCheckBox("Kamera 3");
            textField3 = new JTextField(20);
            panel3 = new JPanel(new FlowLayout());

            panel3.add(checkBox3);
            panel3.add(dropDown3);
            panel3.add(textField3);
            add(panel3, gbc);

            gbc.gridy++;

            startRecording = new JButton("Start");
            startRecording.setSize(75, 50);

            add(startRecording, gbc);
            gbc.gridy++;

            stopRecording = new JButton("Stop");
            stopRecording.setEnabled(false);

            add(stopRecording, gbc);

        }

        private void disableInterface() {
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

        private void enableInterface() {
            checkBox1.setEnabled(true);
            checkBox2.setEnabled(true);
            checkBox3.setEnabled(true);

            textField1.setEnabled(true);
            textField2.setEnabled(true);
            textField3.setEnabled(true);

            dropDown1.setEnabled(true);
            dropDown2.setEnabled(true);
            dropDown3.setEnabled(true);

            startRecording.setEnabled(true);
            stopRecording.setEnabled(false);
        }

    }
}

