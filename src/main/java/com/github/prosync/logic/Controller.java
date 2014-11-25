package com.github.prosync.logic;

import java.io.File;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jim-espen on 10/14/14.
 */
public abstract class Controller {

	abstract boolean getFileHTTP(URL url, File file);

	abstract ArrayList<String> getConnectedNIS() throws SocketException;

	abstract ArrayList<String> getConnectedWIFINIS() throws SocketException;

	abstract String getNISDisplayName(String name) throws SocketException;

	abstract boolean sendCommand(String command, String number);

	abstract boolean sendDeleteCommand(String command);

	abstract String getFilesURL();

	abstract void setModeToVideo();

	abstract void setModeToPhoto();

	abstract void setModeToBurst();

	abstract void setModeToTimelapse();

	abstract void startCamera();

	abstract void stopCamera();

	abstract void setWideFoV();

	abstract void setMediumFoV();

	abstract void setNarrowFoV();

	abstract void setVolumeOff();

	abstract void setVolume70();

	abstract void setVolume100();

	abstract void setContinuousShotSingle();

	abstract void setContinuousShot3SPS();

	abstract void setContinuousShot5SPS();

	abstract void setContinuousShot10SPS();

	abstract void setProTuneOn();

	abstract void setProTuneOff();

	abstract void turnGoProOn();

	abstract void turnGoProOff();

	abstract void setAutoOffNever();

	abstract void setAutoOff60s();

	abstract void setAutoOff120s();

	abstract void setAutoOff300s();

	abstract void setDefaultModeVideo();

	abstract void setDefaultModePhoto();

	abstract void setDefaultModeBurst();

	abstract void setDefaultModeTimelapse();

	abstract void deleteLast();

	abstract void deleteAll();

	public abstract ArrayList<String> getFileListJPG(String URL);

	public abstract ArrayList<String> getFileListMP4(String URL);
}
