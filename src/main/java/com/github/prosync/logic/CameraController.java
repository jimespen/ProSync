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
	static final ArrayList<String> listOfCommands = new ArrayList<String>(Arrays.asList(new String[]{"PW", "CM", "SH", "VV", "FS", "FV", "BS", "WB", "TI", "CS", "BU", "LO", "PT", "DL", "DA", "AO", "DM", "VM"}));

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

}
