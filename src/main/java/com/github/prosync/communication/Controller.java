package com.github.prosync.communication;

/**
 * Created by huseby on 10/3/14.
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;

public class Controller {


	// HTTP GET request

	/**
	 * GET Request method for sending commands to GoPro Cameras
	 * @param url    Example: new URL("http://10.5.5.9/camera/SH?t=<password>&p=%01")
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

	/**
	 * HTTP File retriever, used to downloading files from GoPro Cameras
	 * @param url Example: new URL("http://10.5.5.9:8080/DCIM/105GOPRO/File.mp4")
	 * @param file Example: new File("File.mp4")
	 * @return true if successful, false if not
	 */
	public boolean getFileHTTP(URL url, File file) {
		URLConnection connection = null;
		int i;
		try {
			connection = url.openConnection();
			BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file.getName()));

			while ((i = bis.read()) != -1) {
				bos.write(i);
			}
			bos.flush();
			bis.close();
		} catch (MalformedInputException malformedInputException) {
			malformedInputException.printStackTrace();
			return false;
		} catch (IOException ioException) {
			ioException.printStackTrace();
			return false;
		}
		return true;
	}

}
