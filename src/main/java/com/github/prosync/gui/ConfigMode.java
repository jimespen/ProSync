/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Rubenhag
 */
public class ConfigMode extends JPanel {

    public ConfigMode() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }
                setSize(800,600);
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.anchor = GridBagConstraints.WEST;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                final Mode mode = new Mode("Modus", "Video", "Foto, singel", "Foto, burst mode");
                List<String> values = new ArrayList<>(mode.getValues());

                setBorder(new TitledBorder(mode.getText()));
                ButtonGroup bg = new ButtonGroup();
                for (String value : values) {
                    JRadioButton rb = new JRadioButton(new ModeAction(mode, value));
                    bg.add(rb);
                    add(rb, gbc);
                }

                JButton submit = new JButton("Send til kamera");
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(mode.getSelected());
                    }
                });
                add(submit, gbc);

            }
        });
    }

    public class ModePane extends JPanel {

        public ModePane() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            final Mode mode = new Mode("Modus", "Video", "Foto, singel", "Foto, burst mode");
            List<String> values = new ArrayList<>(mode.getValues());

            setBorder(new TitledBorder(mode.getText()));
            ButtonGroup bg = new ButtonGroup();
            for (String value : values) {
                JRadioButton rb = new JRadioButton(new ModeAction(mode, value));
                bg.add(rb);
                add(rb, gbc);
            }

            JButton submit = new JButton("Send til kamera");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(mode.getSelected());
                }
            });
            add(submit, gbc);

        }
    }

    public class Mode {

        private String text;
        private String value;
        private List<String> values;
        private String selected;

        public Mode(String text, String... value) {
            this.text = text;
            values = new ArrayList<>(Arrays.asList(value));
        }

        public String getText() {
            return text;
        }

        public String getValue() {
            return value;
        }

        public String getSelected() {
            return selected;
        }

        public void setSelected(String selected) {
            this.selected = selected;
        }

        public List<String> getValues() {
            return Collections.unmodifiableList(values);
        }
    }

    public class ModeAction extends AbstractAction {

        private final Mode mode;
        private final String value;

        public ModeAction(Mode mode, String value) {
            this.mode = mode;
            this.value = value;
            putValue(NAME, value);
        }

        public Mode getMode() {
            return mode;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getMode().setSelected(value);
        }
    }
}
