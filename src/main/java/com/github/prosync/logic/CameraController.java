package com.github.prosync.logic;

import com.github.prosync.communication.Connector;
import com.github.prosync.communication.NICResolver;
import com.github.prosync.communication.SocketConnection;
import com.github.prosync.domain.Config;

import java.io.*;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.prosync.domain.Constants;

/**
 * CameraController, controller class for the system.
 */
public class CameraController extends Controller {

    private Connector connector = new Connector();
    private NICResolver NISResolver = new NICResolver();
    private static final ArrayList<String> listOfCommands = new ArrayList<String>(Arrays.asList(new String[]{"PW", "CM", "SH", "VV", "FS", "FV", "BS", "WB", "TI", "CS", "BU", "PT", "DL", "DA", "AO", "DM"}));

    /**
     * sendCommand, generic method for transmitting commands to the cameras.
     * Uses the Connector class's getRequest method.
     *
     * @param command Example: 'SH'
     * @param number  Example: '01'
     * @return true if successful, false if not
     */
    @Override
    boolean sendCommand(NetworkInterface networkInterface, String password, String command, String number) {
        if (listOfCommands.indexOf(command) == -1) {
            return false;
        }
        try {
            String cmd = command + "?t=" + password + "&p=%" + number;
            SocketConnection ce = new SocketConnection(networkInterface, "10.5.5.9", 80);
            ce.sendGetCommand("GET /bacpac/" + cmd + " HTTP/1.1");
            ce.close();

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * sendDeleteCommand, generic method for transmitting commands to the
     * cameras. Uses the Connector class's getRequest method.
     *
     * @param command Example: 'DL'
     * @return true if successful, false if not
     */
    @Override
    boolean sendDeleteCommand(NetworkInterface networkInterface, String password, String command) throws IOException {
        if (listOfCommands.indexOf(command) == -1) {
            return false;
        }
        try {
            String cmd = command + "?t=" + password;
            SocketConnection ce = new SocketConnection(networkInterface, "10.5.5.9", 80);
            ce.sendGetCommand("GET /bacpac/" + cmd + " HTTP/1.1");
            ce.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * sendDeleteCommand, generic method for deleting files on camera
     *
     * @param password Wifi Password to the camera
     * @param command  command to send to camera
     * @return boolean true if OK, false if not
     */
    @Override
    boolean sendDeleteCommand(String password, String command) {
        if (listOfCommands.indexOf(command) == -1) {
            return false;
        }
        try {
            connector.getRequest(new URL("http://10.5.5.9/camera/" + command + "?t=testtest"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * For finding files URL to downloadable files on the GOPRO camera series
     *
     * @return A URL to the files if found, null if not
     */
    public String getFilesURL() {
        Pattern URLPattern = Pattern.compile("[1][0][0-9]GOPRO");
        try {
            String HTMLFile = connector.getHTMLFile(new URL("http://10.5.5.9:8080/DCIM/"));
            Matcher matcherURL = URLPattern.matcher(HTMLFile);

            matcherURL.find();

            return "http://10.5.5.9:8080/DCIM/" + matcherURL.group(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a list containing all .JPG filenames
     *
     * @param URL A URL specifying the URL path to the files
     * @return A list containing the files containing the .JPG filenames, null
     * if camera is empty or not found
     */
    public ArrayList<String> getFileListSingleShot(String URL) {
        System.out.println(URL);
        ArrayList<String> JPGFiles = new ArrayList<String>();
        Pattern JPGPattern = Pattern.compile("GOPR[0-9]*.JPG");
        try {
            String HTMLFile = connector.getHTMLFile(new URL(URL));
            Matcher matcherJPG = JPGPattern.matcher(HTMLFile);
            JPGFiles.add(URL);
            while (matcherJPG.find()) {
                if (!JPGFiles.contains(matcherJPG.group(0))) {
                    JPGFiles.add(matcherJPG.group(0));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JPGFiles;
    }

    /**
     * Creates a list containing all .JPG filenames
     *
     * @param URL A URL specifying the URL path to the files
     * @return A list containing the files containing the .JPG filenames, null
     * if camera is empty or not found
     */
    public ArrayList<String> getFileListBurst(String URL) {
        System.out.println(URL);
        ArrayList<String> JPGFiles = new ArrayList<String>();
        Pattern JPGPattern = Pattern.compile("G[0-9]*.JPG");
        try {
            String HTMLFile = connector.getHTMLFile(new URL(URL));
            System.out.println(URL);
            Matcher matcherJPG = JPGPattern.matcher(HTMLFile);
            JPGFiles.add(URL);
            System.out.println();
            while (matcherJPG.find()) {
                if (!JPGFiles.contains(matcherJPG.group(0))) {
                    JPGFiles.add(matcherJPG.group(0));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JPGFiles;
    }

    /**
     * Creates a list containing all .MP4 filenames
     *
     * @param URL A URL specifying the URL path to the files
     * @return A list containing the files containing the .MP4 filenames, null
     * if camera is empty or not found
     */
    public ArrayList<String> getFileListVideo(String URL) {
        ArrayList<String> MP4Files = new ArrayList<String>();

        try {
            Pattern MP4Pattern = Pattern.compile("GOPR[0-9]*.MP4");
            String HTMLFile = connector.getHTMLFile(new URL(URL));
            Matcher matcherMP4 = MP4Pattern.matcher(HTMLFile);

            while (matcherMP4.find()) {
                if (!MP4Files.contains(matcherMP4.group(0))) {
                    MP4Files.add(matcherMP4.group(0));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return MP4Files;
    }

    /**
     * Method created to list out all WIFI network cards
     *
     * @return ArrayList containing all WIFI nets
     * @throws SocketException
     */
    @Override
    public ArrayList<NetworkInterface> getConnectedWIFINIS() throws SocketException {

        ArrayList<NetworkInterface> NICList = NISResolver.getNIS();
        ArrayList<NetworkInterface> NICWIFIList = new ArrayList<>();

        for (NetworkInterface ni : NICList) {
            Pattern wlanPatern = Pattern.compile("wlan[0-9]*");
            Matcher matcher = wlanPatern.matcher(ni.getName());
            if (matcher.find()) {
                if (!ni.isLoopback() && !ni.isVirtual()) {
                    NICWIFIList.add(ni);
                }
            }
        }
        return NICWIFIList;
    }

    @Override
    public boolean getFileHTTP(URL url, File file) {
        return connector.getFileHTTP(url, file);
    }

    @Override
    public boolean turnGoProOn(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "PW", "01");
    }

    @Override
    public boolean turnGoProOff(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "PW", "00");
    }

    @Override
    public boolean setAutoOffNever(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "AO", "00");
    }

    @Override
    public boolean setAutoOff60s(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "AO", "01");
    }

    @Override
    public boolean setAutoOff120s(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "AO", "02");
    }

    @Override
    public boolean setAutoOff300s(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "AO", "03");
    }

    @Override
    public boolean setDefaultModeVideo(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "DM", "00");
    }

    @Override
    public boolean setDefaultModePhoto(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "DM", "01");
    }

    @Override
    public boolean setDefaultModeBurst(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "DM", "02");
    }

    @Override
    public boolean setDefaultModeTimelapse(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "DM", "03");
    }

    @Override
    public boolean setModeToVideo(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "CM", "00");
    }

    @Override
    public boolean setModeToPhoto(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "CM", "01");
    }

    @Override
    public boolean setModeToBurst(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "CM", "02");
    }

    @Override
    public boolean setModeToTimelapse(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "CM", "03");
    }

    @Override
    public boolean startCamera(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "SH", "01");
    }

    @Override
    public boolean stopCamera(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "SH", "00");
    }

    @Override
    public boolean setWideFoV(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "FV", "00");
    }

    @Override
    public boolean setMediumFoV(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "FV", "01");
    }

    @Override
    public boolean setNarrowFoV(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "FV", "02");
    }

    @Override
    public boolean setVolumeOff(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "BS", "00");
    }

    @Override
    public boolean setVolume70(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "BS", "01");
    }

    @Override
    public boolean setVolume100(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "BS", "02");
    }

    @Override
    public boolean setContinuousShotSingle(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "CS", "00");
    }

    @Override
    public boolean setContinuousShot3SPS(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "CS", "03");
    }

    @Override
    public boolean setContinuousShot5SPS(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "CS", "05");
    }

    @Override
    public boolean setContinuousShot10SPS(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "CS", "0a");
    }

    @Override
    public boolean setProTuneOn(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "PT", "01");
    }

    @Override
    public boolean setProTuneOff(NetworkInterface networkInterface, String password) {
        return sendCommand(networkInterface, password, "PT", "00");
    }

    @Override
    public boolean deleteLast(NetworkInterface networkInterface, String password) throws IOException {
        return sendDeleteCommand(networkInterface, password, "DL");
    }

    @Override
    public boolean deleteAll(NetworkInterface networkInterface, String password) throws IOException {
        return sendDeleteCommand(networkInterface, password, "DA");
    }

    @Override
    public boolean deleteLast(String password) {
        return sendDeleteCommand(password, "DL");
    }

    @Override
    public boolean deleteAll(String password) {
        return sendDeleteCommand(password, "DA");
    }
}
