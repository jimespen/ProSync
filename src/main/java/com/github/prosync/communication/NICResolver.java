/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.communication;

import java.io.IOException;
import java.net.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rubenhag
 */
public class NICResolver {

    public ArrayList<String> getConnectedNICs() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        NetworkInterface nextElement;

		ArrayList<String> list = new ArrayList<>();

        while(interfaces.hasMoreElements()){
            nextElement = interfaces.nextElement();
			list.add(nextElement.getDisplayName());
        }
        return list;
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
