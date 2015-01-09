package com.github.prosync.gui;

import com.github.prosync.domain.Camera;
import com.github.prosync.logic.CameraController;
import com.github.prosync.logic.GUIServices;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

import com.github.prosync.domain.Constants;

/**
 * Created by oystein on 30.11.2014.
 */

public class DownloadMode {

    public DownloadMode() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }

                JFrame frame = new JFrame("Hent filer");
                frame.setPreferredSize(new Dimension(800, 600));
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new DownloadMode.DownloadPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }


    public class DownloadPane extends JPanel {


        public DownloadPane() {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                    }

                    setSize(800, 600);
                    setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.anchor = GridBagConstraints.WEST;
                    gbc.weightx = 1;
                    gbc.gridwidth = 2;
                    gbc.gridy = 0;
                    gbc.gridx = 0;

                    gbc.fill = GridBagConstraints.HORIZONTAL;

                    final DTMDownloadMode dtmDownload = new DTMDownloadMode();

                    final JTable files = new JTable(dtmDownload);

                    JScrollPane jspFiles = new JScrollPane(files);

                    try{
                        ArrayList<String> list = GUIServices.getDownloadableFiles();
                        dtmDownload.addRows(list);
                    }catch (NullPointerException | IllegalStateException e){
                        JOptionPane.showMessageDialog(null, "Fant ingen kamera, bilder eller opptak. Prøv igjen.");
                    }

                    add(jspFiles, gbc);

                    JButton chooseAll = new JButton("Velg alle");
                    JButton chooseNone = new JButton("Velg ingen");
                    JButton chooseGroup = new JButton("Velg samme gruppe");

                    JButton download = new JButton("Last ned");

                    chooseAll.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dtmDownload.checkAll(true);
                        }
                    });
                    chooseNone.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dtmDownload.checkAll(false);
                        }
                    });
                    download.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFileChooser chooser = new JFileChooser();
                            chooser.setCurrentDirectory(new java.io.File("."));
                            chooser.setDialogTitle("Velg lagringsplass");
                            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                            chooser.setAcceptAllFileFilterUsed(false);

                            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                                GUIServices.downloadFiles(dtmDownload.getChecked(), String.valueOf(chooser.getSelectedFile()));
                                JOptionPane.showMessageDialog(null, "Nedlastning ferdig");
                            } else {
                                System.out.println("No Selection ");
                            }
                            System.out.println("Feridg");

                        }
                    });
                    chooseGroup.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            dtmDownload.checkAllSameGroup(files);
                        }
                    });

                    gbc.gridwidth = 1;

                    gbc.gridy++;
                    add(chooseAll, gbc);

                    gbc.gridx++;
                    add(download, gbc);

                    gbc.gridx--;
                    gbc.gridy++;
                    add(chooseNone, gbc);

                    gbc.gridy++;
                    add(chooseGroup, gbc);

                }
            });
        }


        private class DTMDownloadMode extends DefaultTableModel {


            public DTMDownloadMode() {
                addColumn("Navn");
                addColumn("Group");
                addColumn("Mode");
                addColumn("Dato");
                addColumn("Last ned");
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == Constants.DOWNLOAD_COLUMN) return true;
                return false;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case Constants.NAME_COLUMN:
                        return String.class;
                    case Constants.GROUP_COLUMN:
                        return Integer.class;
                    case Constants.MODE_COLUMN:
                        return String.class;
                    case Constants.DATE_COLUMN:
                        return String.class;
                    case Constants.DOWNLOAD_COLUMN:
                        return Boolean.class;
                }
                return null;
            }


            public void checkAll(boolean bol) {
                for (int i = 0; i < getRowCount(); i++) {
                    setValueAt(bol, i, Constants.DOWNLOAD_COLUMN);
                }
            }

            public void checkAllSameGroup(JTable table) {
                Vector rowVectorSelected = (Vector) dataVector.elementAt(table.getSelectedRow());
                int groupSelected = (Integer) rowVectorSelected.elementAt(Constants.GROUP_COLUMN);
                boolean bol = true;
                boolean selectedBol = (Boolean) rowVectorSelected.elementAt(Constants.DOWNLOAD_COLUMN);

                if (selectedBol) bol = false;

                for (int i = 0; i < getRowCount(); i++) {
                    Vector rowVector = (Vector) dataVector.elementAt(i);
                    int group = (Integer) rowVector.elementAt(Constants.GROUP_COLUMN);

                    if (group == groupSelected) setValueAt(bol, i, Constants.DOWNLOAD_COLUMN);
                }
            }

            public ArrayList<String> getChecked() {
                ArrayList<String> checkedRows = new ArrayList<String>();
                for (int i = 0; i < getRowCount(); i++) {
                    Vector rowVector = (Vector) dataVector.elementAt(i);
                    if ((Boolean) rowVector.elementAt(Constants.DOWNLOAD_COLUMN)) {
                        checkedRows.add((String) rowVector.elementAt(Constants.NAME_COLUMN));
                    }
                }
                return checkedRows;
            }

            public void addRows(ArrayList<String> list) {
                for (String s : list) {
                    if (s.contains("JPG") && s.contains("GOPR"))
                        addRow(new Object[]{s, -1, GUIServices.getMode(s), "ToBeImplemented(Maybe)", false});
                    else if (s.contains("JPG") && s.contains("G"))
                        addRow(new Object[]{s, GUIServices.getGroup(s), GUIServices.getMode(s), "ToBeImplemented(Maybe)", false});
                    else if (s.contains("MP4"))
                        addRow(new Object[]{s, -1, GUIServices.getMode(s), "ToBeImplemented(Maybe)", false});
                }

            }
        }

    }
}