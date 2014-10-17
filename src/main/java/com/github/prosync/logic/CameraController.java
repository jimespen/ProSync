package com.github.prosync.logic;

import com.github.prosync.communication.Connector;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jim-espen on 10/14/14.
 */
public class CameraController extends Controller {
	Connector connector = new Connector();
	static final ArrayList<String> listOfCommands = new ArrayList<String>(Arrays.asList(new String[]{"PW", "CM", "SH", "VV", "FS", "FV", "BS", "WB", "TI", "CS", "BU", "PT", "DL", "DA", "AO", "DM"}));

	/**
	 * sendCommand, generic method for transmitting commands to the cameras.
	 * Uses the Connector class's getRequest method.
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
