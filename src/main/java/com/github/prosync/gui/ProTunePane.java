/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.gui;

import com.github.prosync.domain.Config;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Ruben
 */
public class ProTunePane extends JPanel {

    public ProTunePane(JFrame contentFrame, Config conf) {
        final Config config = conf;
        final JFrame frame = contentFrame;
        setSize(800, 600);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        ButtonGroup bg = new ButtonGroup();
        JRadioButton yes = new JRadioButton(new ProTuneAction(config, true));
        JRadioButton no = new JRadioButton(new ProTuneAction(config, false));
        yes.setText("JA");
        no.setText("NEI");
        bg.add(yes);
        bg.add(no);
        add(yes, gbc);
        add(no, gbc);
        JButton submit = new JButton("Send til kamera");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(config.getProTuneSlected());

            }
        });
        add(submit, gbc);

    }

    public class ProTuneAction extends AbstractAction {

        private final Config config;
        private final boolean value;

        public ProTuneAction(Config mode, boolean value) {
            this.config = mode;
            this.value = value;
        }

        public Config getConfig() {
            return config;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getConfig().setProTuneSelected(value);
        }
    }
}
