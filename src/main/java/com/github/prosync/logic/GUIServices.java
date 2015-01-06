package com.github.prosync.logic;

import com.github.prosync.domain.Camera;
import com.github.prosync.domain.Config;
import com.github.prosync.domain.Constants;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huseby on 11/27/14.
 * Class GUIServices,
 * Created to execute all inner commands a GUI normally would.
 */
public final class GUIServices {
    private static final CameraController cc = new CameraController();
    private static ArrayList<Camera> cams = new ArrayList<>();
    private static Config config = new Config();

    /**
     * A method to get all the conntected WIFI interfaces
     * @return returns a ArrayList with NetworkInterfaces that are up and not virtual.
     * @throws SocketException
     */
    public static ArrayList<NetworkInterface> getConectedWIFINICS() throws SocketException {
        return cc.getConnectedWIFINIS();
    }


    public static ArrayList<String> getConectedWIFINames() throws SocketException {
        ArrayList<String> list = new ArrayList<>();

        for(NetworkInterface ni: cc.getConnectedWIFINIS())
        list.add(ni.getDisplayName());

        return list;
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
        return cc.getFileListSingleShot(cc.getFilesURL());
    }

	public static ArrayList<String> getDownloadableFiles(){
		String url = cc.getFilesURL();
		ArrayList<String> list = new ArrayList<>();
		for(String s:cc.getFileListVideo(url))list.add(s);
		for(String s:cc.getFileListSingleShot(url))list.add(s);
		for(String s:cc.getFileListBurst(url))list.add(s);
		return list;
	}

    public static String getMode(String name){
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

    public static int getGroup(String name){
        Pattern groupPattern = Pattern.compile("G[0-9][0-9][0-9]");
        Matcher matcherPattern = groupPattern.matcher(name);
        if(matcherPattern.find()){
            String group = matcherPattern.group(0);
            group = group.replace("G", "");
            return Integer.parseInt(group);
        }
        return -1;
    }
    
    
    public static void downloadFiles(ArrayList<String> files, String saveLocation){
        String URL = cc.getFilesURL();
		for(String s: files){
            String savePlace  = saveLocation.replace('\\', '/');

            File f = new File(savePlace+"/"+s);
			try {
                cc.getFileHTTP(new URL(URL+"/"+s), f);
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

    public static void clearCameraList(){
        cams = new ArrayList<>();
    }

    public static void addCamera(String name, NetworkInterface ni, String password){
        cams.add(new Camera(name, ni, password));
    }

    public static void updateCameras(ArrayList<Camera> updatedCams){
        if(updatedCams.size() == cams.size())
        cams = updatedCams;
    }

    public static void startShutter(){
        for(Camera c: cams){
            cc.startCamera(c.getNic(), c.getPassword());
        }
    }

    public static void stopShutter(){
        for(Camera c: cams){
            cc.stopCamera(c.getNic(), c.getPassword());
        }
    }
    
    public static void sendConfig(Config config){
        for(Camera c : cams){
            sendConfigToCamera(config, c.getNic(), c.getPassword());
        }
    }
    
    private static void sendConfigToCamera(Config config, NetworkInterface networkInterface, String password){
        switch (config.getModeSelected()) {
            case Constants.VIDEO_MODE:
                cc.setModeToVideo(networkInterface, password);
                break;
            case Constants.BURST_MODE:
                cc.setModeToBurst(networkInterface, password);
                break;
            case Constants.PHOTO_MODE:
                cc.setModeToPhoto(networkInterface, password);
                break;

        }

        switch (config.getResolutionSelected()) {
            case Constants.FOUR_K:
                cc.sendCommand(networkInterface, password, "VV", "06");
                break;
            case Constants.FOUR_K_SEVENTEEN_NINE:
                cc.sendCommand(networkInterface, password, "VV", "08");
                break;
            case Constants.TWO_POINT_SEVEN_K:
                cc.sendCommand(networkInterface, password, "VV", "05");
                break;
            case Constants.FOURTEEN_FOURTY_P:
                cc.sendCommand(networkInterface, password, "VV", "04");
                break;
            case Constants.TEN_EIGHTY_P:
                cc.sendCommand(networkInterface, password, "VV", "03");
                break;
            case Constants.TEN_EIGHTY_SV:
                cc.sendCommand(networkInterface, password, "VV","09");
                break;
            case Constants.NINE_SIXTY_P:
                cc.sendCommand(networkInterface, password, "VV" , "02");
                break;
            case Constants.SEVEN_TWENTY_P:
                cc.sendCommand(networkInterface, password, "VV", "01");
                break;
            case Constants.SEVEN_TWENTY_SV:
                cc.sendCommand(networkInterface, password, "VV", "0a");
                break;
            case Constants.FIVE_MP:
                cc.sendCommand(networkInterface, password, "PR", "03");
                break;
            case Constants.SEVEN_MP:
                cc.sendCommand(networkInterface, password, "PR" , "04");
                break;
            case Constants.TWELVE_MP:
                cc.sendCommand(networkInterface, password, "PR", "05");
        }
        
        switch(config.getFpsSelected()){
            case Constants.TWELVE_FPS:
                cc.sendCommand(networkInterface, password, "FS" , "00");
                break;
            case Constants.TWELVE_POINT_FIVE_FPS:
                cc.sendCommand(networkInterface, password, "FS", "0b");
                break;
            case Constants.TWENTY_FOUR_FPS:
                cc.sendCommand(networkInterface, password, "FS", "02");
                break;
            case Constants.TWENTY_FIVE_FPS:
                cc.sendCommand(networkInterface, password, "FS", "03");
                break;
            case Constants.FOURTY_EIGHT_FPS:
                cc.sendCommand(networkInterface, password, "FS", "05");
                break;
            case Constants.FIFTY_FPS:
                cc.sendCommand(networkInterface, password, "FS", "06");
                break;
            case Constants.ONE_HUNDRED_FPS:
                cc.sendCommand(networkInterface, password, "FS" ,"08");
                break;
            case Constants.TWO_HUNDRED_FOURTY_FPS:
                cc.sendCommand(networkInterface, password, "FS", "0a");
                break;
        }
        
        if(config.getProTuneSlected()){
            cc.setProTuneOn(networkInterface, password);
        } else {
            cc.setProTuneOff(networkInterface, password);
        }
    }
    
    public static Config getConfig(){
        return config;
    }
    

}
