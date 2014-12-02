package com.github.prosync.logic;

import java.io.File;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return cc.getFileListSingleShot(cc.getFilesURL());
    }

	public ArrayList<String> getDownloadableFiles(){
		String url = cc.getFilesURL();
		ArrayList<String> list = new ArrayList<>();
		for(String s:cc.getFileListVideo(url))list.add(s);
		for(String s:cc.getFileListSingleShot(url))list.add(s);
		for(String s:cc.getFileBurst(url))list.add(s);
		return list;
	}

	public String getMode(String name){
		String url = cc.getFilesURL();

		Pattern singleShotPattern = Pattern.compile("GOPR[0-9]*.JPG");
		Pattern burstPattern = Pattern.compile("G[0-9]*.JPG");
		Pattern videoPattern = Pattern.compile("GOPR[0-9]*.MP4");

		Matcher matcherSingleShot = singleShotPattern.matcher(name);
		Matcher matcherBurst = burstPattern.matcher(name);
		Matcher matcherVideo = videoPattern.matcher(name);


		try{
			if(matcherBurst.find()) return "Burst";
			if(matcherSingleShot.find()) return "Single";
			if(matcherVideo.find()) return "Video";

		}catch (IllegalStateException e){
		}
		return "N/A";
	}

	public int getGroup(String name){
		Pattern groupPattern = Pattern.compile("G[0-9][0-9][0-9]");
		Matcher matcherPattern = groupPattern.matcher(name);
		if(matcherPattern.find()){
			String group = matcherPattern.group(0);
			group = group.replace("G", "");
			return Integer.parseInt(group);
		}
		return -1;
	}

    public void downloadFiles(ArrayList<String> files, String saveLocation){
        String URL = cc.getFilesURL();
		for(String s: files){
            File f = new File(saveLocation+"/"+s);
			try {
                cc.getFileHTTP(new URL(URL), f);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

    }

}
