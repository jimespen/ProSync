package network;

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
	 * @param url    Example: new URL("http://10.5.5.9/bacpac/SH?t=testtest&p=%01")
	 * @param userAgent Example: Mozilla/5.0
	 * @return returns true if successful, false if not
	 */
	public boolean getRequest(URL url, String userAgent) {
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
		} catch (Exception e) {
			System.out.println("GET Request failed, check settings parameteres and that you are connected to camera");
			return false;
		}
		return true;
	}

	/**
	 * HTTP File retriver, used to downloading files from GoPro Cameras
	 * @param url Example: new URL("http://10.5.5.9/bacpac/SH?t=testtest&p=%01")
	 * @param saveLocation Example: new File("C:\File.mp4")
	 * @return returns true if successful, false if not
	 */
	public boolean getFileHTTP(URL url, File saveLocation) {
		URLConnection con = null;
		int i;
		try {
			con = url.openConnection();
			BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(saveLocation.getName()));

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
