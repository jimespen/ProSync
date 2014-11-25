package com.github.prosync.logic;

import com.github.prosync.communication.Connector;
import com.github.prosync.communication.NICResolver;

import java.io.*;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jim-espen on 10/14/14.
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
	boolean sendCommand(String command, String number) {
		if (listOfCommands.indexOf(command) == -1)
			return false;
		try {
			connector.getRequest(new URL("http://10.5.5.9/camera/" + command + "?t=testtest&p=%" + number));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * sendDeleteCommand, generic method for transmitting commands to the cameras.
	 * Uses the Connector class's getRequest method.
	 *
	 * @param command Example: 'DL'
	 * @return true if successful, false if not
	 */
	@Override
	boolean sendDeleteCommand(String command) {
		if (listOfCommands.indexOf(command) == -1)
			return false;
		try {
			connector.getRequest(new URL("http://10.5.5.9/camera/" + command + "?t=testtest"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * For finding files URL to downloadable files on the GOPRO camera series
	 * @return A URL to the files if found, null if not
	 */
	public String getFilesURL(){
		Pattern URLPattern = Pattern.compile("[1][0][0-9]GOPRO");
		try {
			String HTMLFile = connector.getHTMLFile(new URL("http://10.5.5.9:8080/DCIM/"));
			Matcher matcherURL = URLPattern.matcher(HTMLFile);

			matcherURL.find();

			return "http://10.5.5.9:8080/DCIM/"+matcherURL.group(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * Creates a list containing all .JPG filenames
	 * @param URL A URL specifying the URL path to the files
	 * @return A list containing the files containing the .JPG filenames, null if camera is empty or not found
	 */
	public ArrayList<String> getFileListJPG(String URL) {
		ArrayList<String> JPGFiles = new ArrayList<String>();
		Pattern JPGPattern = Pattern.compile("G[0-9]*.JPG");
		try {
			String HTMLFile = connector.getHTMLFile(new URL(URL));
			System.out.println(URL);
			Matcher matcherJPG = JPGPattern.matcher(HTMLFile);
			JPGFiles.add(URL);

			while (matcherJPG.find()) {
				if(!JPGFiles.contains(matcherJPG.group(0))){JPGFiles.add(matcherJPG.group(0));}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JPGFiles;
	}

	/**
	 * Creates a list containing all .MP4 filenames
	 * @param URL A URL specifying the URL path to the files
	 * @return A list containing the files containing the .MP4 filenames, null if camera is empty or not found
	 */
	public ArrayList<String> getFileListMP4(String URL) {
		ArrayList<String> MP4Files = new ArrayList<String>();

		try {
			Pattern MP4Pattern = Pattern.compile("GOPR[0-9]*.MP4");
			String HTMLFile = connector.getHTMLFile(new URL(URL));
			Matcher matcherMP4 = MP4Pattern.matcher(HTMLFile);

			while (matcherMP4.find()) {
				if(!MP4Files.contains(matcherMP4.group(0))){MP4Files.add(matcherMP4.group(0));}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return MP4Files;
	}

	@Override
	public ArrayList<String> getConnectedNIS() throws SocketException {
		return NISResolver.getConnectedNICs();
	}

	@Override
	public ArrayList<String> getConnectedWIFINIS() throws SocketException {

		ArrayList<String> NICList = NISResolver.getConnectedNICs();
		ArrayList<String> NICWIFIList = new ArrayList<>();

		for(String s:NICList){
			Pattern wlanPatern = Pattern.compile("wlan[0-9]*");
			Matcher matcher = wlanPatern.matcher(s);
			if(matcher.find()){
				NICWIFIList.add(matcher.group(0));
			}
		}
		return NICWIFIList;
	}

	@Override
	public String getNISDisplayName(String name) throws SocketException {
		return NISResolver.getDisplayName(name);
	}

	@Override
	public boolean getFileHTTP(URL url, File file){
		return connector.getFileHTTP(url,file);
	}

	@Override
	public void turnGoProOn() {
		sendCommand("PW", "01");
	}

	@Override
	public void turnGoProOff() {
		sendCommand("PW", "00");
	}

	@Override
	public void setAutoOffNever() {
		sendCommand("AO", "00");
	}

	@Override
	public void setAutoOff60s() {
		sendCommand("AO", "01");
	}

	@Override
	public void setAutoOff120s() {
		sendCommand("AO", "02");
	}

	@Override
	public void setAutoOff300s() {
		sendCommand("AO", "03");
	}

	@Override
	public void setDefaultModeVideo() {
		sendCommand("DM", "00");
	}

	@Override
	public void setDefaultModePhoto() {
		sendCommand("DM", "01");
	}

	@Override
	public void setDefaultModeBurst() {
		sendCommand("DM", "02");
	}

	@Override
	public void setDefaultModeTimelapse() {
		sendCommand("DM", "03");
	}

	@Override
	public void setModeToVideo() {
		sendCommand("CM", "00");
	}

	@Override
	public void setModeToPhoto() {
		sendCommand("CM", "01");
	}

	@Override
	public void setModeToBurst() {
		sendCommand("CM", "02");
	}

	@Override
	public void setModeToTimelapse() {
		sendCommand("CM", "03");
	}

	@Override
	public void startCamera() {
		sendCommand("SH", "01");
	}

	@Override
	public void stopCamera() {
		sendCommand("SH", "00");
	}

	@Override
	public void setWideFoV() {
		sendCommand("FV", "00");
	}

	@Override
	public void setMediumFoV() {
		sendCommand("FV", "01");
	}

	@Override
	public void setNarrowFoV() {
		sendCommand("FV", "02");
	}

	@Override
	public void setVolumeOff() {
		sendCommand("BS", "00");
	}

	@Override
	public void setVolume70() {
		sendCommand("BS", "01");
	}

	@Override
	public void setVolume100() {
		sendCommand("BS", "02");
	}

	@Override
	public void setContinuousShotSingle() {
		sendCommand("CS", "00");
	}

	@Override
	public void setContinuousShot3SPS() {
		sendCommand("CS", "03");
	}

	@Override
	public void setContinuousShot5SPS() {
		sendCommand("CS", "05");
	}

	@Override
	public void setContinuousShot10SPS() {
		sendCommand("CS", "0a");
	}

	@Override
	public void setProTuneOn() {
		sendCommand("PT", "01");
	}

	@Override
	public void setProTuneOff() {
		sendCommand("PT", "00");
	}

	@Override
	public void deleteLast() {
		sendDeleteCommand("DL");
	}

	@Override
	public void deleteAll() {
		sendDeleteCommand("DA");
	}
}
