package com.github.prosync.logic;

import com.github.prosync.communication.Connector;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jim-espen on 10/14/14.
 */
public class CameraController extends Controller {
	Connector connector = new Connector();
	static final ArrayList<String> listOfCommands = new ArrayList<String>(Arrays.asList(new String[]{"PW", "CM", "SH", "VV", "FS", "FV", "BS", "WB", "TI", "CS", "BU", "PT", "DL", "DA", "AO", "DM"}));

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
	 * @return
	 */
	public ArrayList<String> getFileList() {
		ArrayList<String> JPEGFiles = new ArrayList<String>();
		ArrayList<String> MP4Files = new ArrayList<String>();

		try {
			Pattern URLPattern = Pattern.compile("[1][0][0-9]GOPRO");
			Pattern JPEGPattern = Pattern.compile("G[0-9]*.JPG");
			Pattern MP4Pattern = Pattern.compile("GOPR[0-9]*.MP4");

			String HTMLFile = connector.getHTMLFile(new URL("http://10.5.5.9:8080/DCIM/"));

			Matcher m = URLPattern.matcher(HTMLFile);
			m.find();
			String test = m.group(0);

			String HTMLFile2 = connector.getHTMLFile(new URL("http://10.5.5.9:8080/DCIM/"+test));

			//String temp = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\"><head>\t<title></title>\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body>\t<div id=\"main\" style=\"height: 100%; text-align: center;\"><!-- BEGINOF main -->\t  <img src=\"/icons/gopro_logo.png\" class=\"logo\" />\t  <pre></pre>\t<div id=\"dirlist\"><!-- BEGINOF dirlist -->\t<table>\t\t<thead class=\"headings\">\t\t  <tr>\t\t\t<td style=\"width: 1px;\">&nbsp;</td>\t\t\t<td>\t\t\t<a href=\"?order=N\">Name</a>\t\t\t</td>\t\t\t<td>\t\t\t<a href=\"?order=s\">Size</a>\t\t\t</td>\t\t  </tr>\t\t</thead>\t\t<tbody><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010684.JPG\">G0010684.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.0M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010685.JPG\">G0010685.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.8M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010686.JPG\">G0010686.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.3M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010687.JPG\">G0010687.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.3M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010688.JPG\">G0010688.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.3M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010689.JPG\">G0010689.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.3M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010690.JPG\">G0010690.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010691.JPG\">G0010691.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.3M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010692.JPG\">G0010692.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.3M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010693.JPG\">G0010693.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010694.JPG\">G0010694.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010695.JPG\">G0010695.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010696.JPG\">G0010696.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010697.JPG\">G0010697.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010698.JPG\">G0010698.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010699.JPG\">G0010699.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010700.JPG\">G0010700.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010701.JPG\">G0010701.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010702.JPG\">G0010702.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010703.JPG\">G0010703.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010704.JPG\">G0010704.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010705.JPG\">G0010705.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010706.JPG\">G0010706.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010707.JPG\">G0010707.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010708.JPG\">G0010708.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.5M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010709.JPG\">G0010709.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010710.JPG\">G0010710.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010711.JPG\">G0010711.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010712.JPG\">G0010712.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr><tr>  <td style=\"width: 1px;\">    <img class=\"icon\" src=\"/icons/camera.png\" alt=\"[   ]\" />  </td>  <td>    <a class=\"link\" href=\"G0010713.JPG\">G0010713.JPG</a>  </td>  <td class=\"size\">    <span class=\"size\">2</span><span class=\"unit\">.4M</span>  </td></tr>\t\t</tbody>\t\t</table>\t</div><!-- ENDOF dirlist -->\t</div><!-- ENDOF main--></body></html>\n";

			Matcher m2 = JPEGPattern.matcher(HTMLFile2);
			Matcher m3 = MP4Pattern.matcher(HTMLFile2);



			while (m2.find()) {
				if(!JPEGFiles.contains(m2.group(0))){JPEGFiles.add(m2.group(0));}
			}

			while (m3.find()) {
				if(!MP4Files.contains(m3.group(0))){MP4Files.add(m3.group(0));}
			}

			for(String s:JPEGFiles){
				System.out.println("Downloading: " + s);
				connector.getFileHTTP(new URL("http://10.5.5.9:8080/DCIM/" + test + "/" + s), new File("~/images/"+s));
			}


		} catch (Exception e) {
		}

		return null;
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