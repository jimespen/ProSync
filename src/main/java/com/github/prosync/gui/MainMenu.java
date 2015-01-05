package com.github.prosync.gui;

import com.github.prosync.logic.GUIServices;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jim-espen on 10/17/14.
 */
public class MainMenu {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    final static ButtonListener listener = ButtonListener.getInstance();

    private JButton buttonRecord;
    private JButton buttonDownload;
    private JButton buttonCofig;

    public MainMenu() {
        buttonRecord = new JButton("Opptak");
        buttonCofig = new JButton("Config");
        buttonDownload = new JButton("Hent filer");
    }

    public void addComponentsToPane(Container pane) {
        addActionListeners();

        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        button = new JButton("Konfigurer");
        button.setActionCommand("conf");
        button.addActionListener(listener);
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton("Hent ut");
        button.setActionCommand("getdata");
        button.addActionListener(listener);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(buttonDownload, c);

        button = new JButton("Opptak");
        button.setActionCommand("rec");
        button.addActionListener(listener);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        pane.add(buttonRecord, c);

    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ProSync");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private void addActionListeners() {
        buttonRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        }

                        JFrame frame = new JFrame("Last ned");
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
        });

        buttonDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        }

                        JFrame frame = new JFrame("Last ned");
                        frame.setPreferredSize(new Dimension(1000, 600));
                        JTabbedPane config = new JTabbedPane();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setLayout(new BorderLayout());
                        config.add(new DownloadMode());
                        frame.add(config);
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });
            }
        });
    }
}


