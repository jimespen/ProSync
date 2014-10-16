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
	Connector connector;
	static final ArrayList<String> listOfCommands = new ArrayList<String>(Arrays.asList(new String[]{"PW", "CM", "SH", "VV", "FS", "FV", "BS", "WB", "TI", "CS", "BU", "PT", "DL", "DA", "AO", "DM"}));

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

	@Override
	void turnGoProOn() {
		sendCommand("PW", "01");
	}

	@Override
	void turnGoProOff() {
		sendCommand("PW", "00");
	}

	@Override
	void setAutoOffNever() {
		sendCommand("AO", "00");
	}

	@Override
	void setAutoOff60s() {
		sendCommand("AO", "01");
	}

	@Override
	void setAutoOff120s() {
		sendCommand("AO", "02");
	}

	@Override
	void setAutoOff300s() {
		sendCommand("AO", "03");
	}

	@Override
	void setDefaultModeVideo() {
		sendCommand("DM", "00");
	}

	@Override
	void setDefaultModePhoto() {
		sendCommand("DM", "01");
	}

	@Override
	void setDefaultModeBurst() {
		sendCommand("DM", "02");
	}

	@Override
	void setDefaultModeTimelapse() {
		sendCommand("DM", "03");
	}

	@Override
	void setModeToVideo() {
		sendCommand("CM", "00");
	}

	@Override
	void setModeToPhoto() {
		sendCommand("CM", "01");
	}

	@Override
	void setModeToBurst() {
		sendCommand("CM", "02");
	}

	@Override
	void setModeToTimelapse() {
		sendCommand("CM", "03");
	}

	@Override
	void startCamera() {
		sendCommand("SH", "01");
	}

	@Override
	void stopCamera() {
		sendCommand("SH", "00");
	}

	@Override
	void setWideFoV() {
		sendCommand("FV", "00");
	}

	@Override
	void setMediumFoV() {
		sendCommand("FV", "01");
	}

	@Override
	void setNarrowFoV() {
		sendCommand("FV", "02");
	}

	@Override
	void setVolumeOff() {
		sendCommand("BS", "00");
	}

	@Override
	void setVolume70() {
		sendCommand("BS", "01");
	}

	@Override
	void setVolume100() {
		sendCommand("BS", "02");
	}

	@Override
	void setContinuousShotSingle() {
		sendCommand("CS", "00");
	}

	@Override
	void setContinuousShot3SPS() {
		sendCommand("CS", "03");
	}

	@Override
	void setContinuousShot5SPS() {
		sendCommand("CS", "05");
	}

	@Override
	void setContinuousShot10SPS() {
		sendCommand("CS", "0a");
	}

	@Override
	void setProTuneOn() {
		sendCommand("PT", "01");
	}

	@Override
	void setProTuneOff() {
		sendCommand("PT", "00");
	}

}
