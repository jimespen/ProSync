package com.github.prosync.logic;

import java.io.File;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by huseby on 11/27/14.
 */
public class GUIServices {
    private final CameraController cc = new CameraController();

    /**
     * A method to get all the conntected WIFI interfaces
     * @return returns a ArrayList with NetworkInterfaces that are up and not virtual.
     * @throws SocketException
     */
    public ArrayList<NetworkInterface> getConectedWIFINICS() throws SocketException {
        return cc.getConnectedWIFINIS();
    }

    /**
     * Finds a specified Interface given a DisplayName
     * @param displayName
     * @return NetworkInterface object with the given DisplayName
     * @throws SocketException
     */
    public NetworkInterface findInterface(String displayName) throws SocketException {

        for(NetworkInterface ni:cc.getConnectedWIFINIS()){
            if(ni.getDisplayName().equals(displayName)) return ni;
        }
        return null;
    }

    public ArrayList<String> getDownloadableJPEGFiles(){
        return cc.getFileListJPG(cc.getFilesURL());
    }

	public ArrayList<String> getDownloadableFiles(){
		String url = cc.getFilesURL();
		ArrayList<String> list = new ArrayList<>();
		for(String s:cc.getFileListMP4(url))list.add(s);
		for(String s:cc.getFileListJPG(url))list.add(s);
		return list;
	}
    public void downloadFiles(ArrayList<String> files, String saveLocation){
        String URL = cc.getFilesURL();
		System.out.println(URL);
		for(String s: files){
            File f = new File(saveLocation+"/"+s);
			System.out.println(f.getPath());
			try {
                cc.getFileHTTP(new URL(URL), f);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

    }

}
