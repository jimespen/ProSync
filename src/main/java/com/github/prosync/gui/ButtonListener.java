/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.prosync.gui;

import com.github.prosync.domain.Config;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import com.github.prosync.logic.GUIServices;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rubenhag
 */
public class ButtonListener implements ActionListener{
    final private static ButtonListener instance = new ButtonListener();
    
    private ButtonListener(){
        
    }
    
    public static ButtonListener getInstance(){
        return instance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        switch (button.getActionCommand()){
            case "conf":{
                new ConfigMode();
              break;   
            }
            case "getdata":
        try {
            GUIServices.shutDownCameras();
        } catch (IOException ex) {
            Logger.getLogger(ButtonListener.class.getName()).log(Level.SEVERE, null, ex);
        }
                break;
                
            case "rec":
                new ConfigProTune(new Config());
                break;
                
        }
    }
    
}
