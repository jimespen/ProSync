package com.github.prosync.communication;

/**
 * Created by huseby on 10/3/14.
 */

import java.net.HttpURLConnection;
import java.net.URL;

public class Connector {


	// HTTP GET request

	/**
	 * GET Request method for sending commands to GoPro Cameras
	 *
	 * @param url Example: new URL("http://10.5.5.9/camera/SH?t=<password>&p=%01")
	 * @return true if successful, false if not
	 */
	public boolean getRequest(URL url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
		} catch (Exception e) {
			System.out.println("GET Request failed, check settings parameteres and that you are connected to camera");
			return false;
		}
		return true;
	}

}
