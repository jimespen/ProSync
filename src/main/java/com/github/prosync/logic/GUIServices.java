package com.github.prosync.logic;

import java.lang.reflect.Array;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by huseby on 11/27/14.
 */
public class GUIServices {
    private final CameraController cc = new CameraController();

    private ArrayList<NetworkInterface> getConectedWIFINICS() throws SocketException {
        return cc.getConnectedWIFINIS();
    }

    private NetworkInterface findInterface(NetworkInterface ni) throws SocketException {
        ArrayList<NetworkInterface> interfaces = cc.getConnectedWIFINIS();

        for(NetworkInterface nis:interfaces){
            if(nis.getName().equals(ni.getDisplayName())){
                return ni;
            }
        }
        return null;
    }

    public ArrayList<String> getConnectedWIFIDisplayName() throws SocketException {
        ArrayList<String> displayNames = new ArrayList<>();
        for(NetworkInterface ni:getConectedWIFINICS()){
            displayNames.add(ni.getDisplayName());
        }
        return displayNames;
    }
}
