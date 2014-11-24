/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.prosync.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

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
                //new ConfigMode();
              break;   
            }
            case "getdata":
                break;
                
            case "rec":
                break;
                
        }
    }
    
}
