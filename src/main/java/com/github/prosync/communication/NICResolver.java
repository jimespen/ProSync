/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.communication;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NICResolver {

    public ArrayList<NetworkInterface> getNIS() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        NetworkInterface nextElement;
		ArrayList<NetworkInterface> list = new ArrayList<>();

        while(interfaces.hasMoreElements()){
 
            nextElement = interfaces.nextElement();
			list.add(nextElement);
		}
        return list;
    }
}
