/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.communication;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rubenhag
 */
public class NICResolver {

    private ArrayList<String> connectedNICs;

    public NICResolver() {
        connectedNICs = new ArrayList();
    }

    public ArrayList getConnectedNICs() throws SocketException, IOException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        NetworkInterface nextElement = null;
        while(interfaces.hasMoreElements()){
            nextElement = interfaces.nextElement();
            try{
            new Thread(new Connection(nextElement.getName(),0)).start();
            } catch (SocketException e){
                e.printStackTrace(System.err);
                System.out.println(nextElement.getName()+" is not connected");
            }
        }

        return connectedNICs;
    }

    public static void main(String[] args){
        NICResolver nicr = new NICResolver();
        try {
            nicr.getConnectedNICs();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
