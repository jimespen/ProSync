package com.github.prosync.logic;

import com.github.prosync.domain.Config;

import java.io.File;
import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Class Controller
 * Abstract Controller class
 */
public abstract class Controller {

    abstract boolean getFileHTTP(URL url, File file);

    abstract ArrayList<java.net.NetworkInterface> getConnectedWIFINIS() throws SocketException;

    abstract boolean sendCommand(NetworkInterface networkInterface, String password, String command, String number);

    abstract boolean sendDeleteCommand(NetworkInterface networkInterface, String password, String command) throws IOException;

    abstract boolean sendDeleteCommand(String password, String command);

    abstract String getFilesURL();

    abstract void setModeToVideo(NetworkInterface networkInterface, String password);

    abstract void setModeToPhoto(NetworkInterface networkInterface, String password);

    abstract void setModeToBurst(NetworkInterface networkInterface, String password);

    abstract void setModeToTimelapse(NetworkInterface networkInterface, String password);

    abstract void startCamera(NetworkInterface networkInterface, String password);

    abstract void stopCamera(NetworkInterface networkInterface, String password);

    abstract void setWideFoV(NetworkInterface networkInterface, String password);

    abstract void setMediumFoV(NetworkInterface networkInterface, String password);

    abstract void setNarrowFoV(NetworkInterface networkInterface, String password);

    abstract void setVolumeOff(NetworkInterface networkInterface, String password);

    abstract void setVolume70(NetworkInterface networkInterface, String password);

    abstract void setVolume100(NetworkInterface networkInterface, String password);

    abstract void setContinuousShotSingle(NetworkInterface networkInterface, String password);

    abstract void setContinuousShot3SPS(NetworkInterface networkInterface, String password);

    abstract void setContinuousShot5SPS(NetworkInterface networkInterface, String password);

    abstract void setContinuousShot10SPS(NetworkInterface networkInterface, String password);

    abstract void setProTuneOn(NetworkInterface networkInterface, String password);

    abstract void setProTuneOff(NetworkInterface networkInterface, String password);

    abstract void turnGoProOn(NetworkInterface networkInterface, String password);

    abstract void turnGoProOff(NetworkInterface networkInterface, String password);

    abstract void setAutoOffNever(NetworkInterface networkInterface, String password);

    abstract void setAutoOff60s(NetworkInterface networkInterface, String password);

    abstract void setAutoOff120s(NetworkInterface networkInterface, String password);

    abstract void setAutoOff300s(NetworkInterface networkInterface, String password);

    abstract void setDefaultModeVideo(NetworkInterface networkInterface, String password);

    abstract void setDefaultModePhoto(NetworkInterface networkInterface, String password);

    abstract void setDefaultModeBurst(NetworkInterface networkInterface, String password);

    abstract void setDefaultModeTimelapse(NetworkInterface networkInterface, String password);

    abstract void deleteLast(NetworkInterface networkInterface, String password) throws IOException;

    abstract void deleteAll(NetworkInterface networkInterface, String password) throws IOException;

    abstract void deleteLast(String password);

    abstract void deleteAll(String password);

    public abstract ArrayList<String> getFileListSingleShot(String URL);

    public abstract ArrayList<String> getFileListVideo(String URL);

}
