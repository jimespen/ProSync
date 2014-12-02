package com.github.prosync.logic;

import java.io.File;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import com.github.prosync.communication.Connection;
import java.io.IOException;

/**
 * Created by huseby on 11/27/14.
 */
public final class GUIServices {
    private static final CameraController cc = new CameraController();

    /**
     * A method to get all the conntected WIFI interfaces
     * @return returns a ArrayList with NetworkInterfaces that are up and not virtual.
     * @throws SocketException
     */
    public static ArrayList<NetworkInterface> getConectedWIFINICS() throws SocketException {
        return cc.getConnectedWIFINIS();
    }

    /**
     * Finds a specified Interface given a DisplayName
     * @param displayName
     * @return NetworkInterface object with the given DisplayName
     * @throws SocketException
     */
    public static NetworkInterface findInterface(String displayName) throws SocketException {

        for(NetworkInterface ni:cc.getConnectedWIFINIS()){
            if(ni.getDisplayName().equals(displayName)) return ni;
        }
        return null;
    }

    public static ArrayList<String> getDownloadableJPEGFiles(){
        return cc.getFileListJPG(cc.getFilesURL());
    }

	public ArrayList<String> getDownloadableFiles(){
		String url = cc.getFilesURL();
		ArrayList<String> list = new ArrayList<>();
		for(String s:cc.getFileListMP4(url))list.add(s);
		for(String s:cc.getFileListJPG(url))list.add(s);
		return list;
	}
    public static void downloadFiles(ArrayList<String> files, String saveLocation){
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
    
    public static void shutDownCameras() throws IOException{
        /*
        Connection con = new Connection("wlan0",0);
        Connection con2 = new Connection("wlan3",0);
        con.setCommand("PW", "01");
        con2.setCommand("PW", "01");
        con.run();
        con2.run();
                */
    }
    

}
