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

    abstract boolean setModeToVideo(NetworkInterface networkInterface, String password);

    abstract boolean setModeToPhoto(NetworkInterface networkInterface, String password);

    abstract boolean setModeToBurst(NetworkInterface networkInterface, String password);

    abstract boolean setModeToTimelapse(NetworkInterface networkInterface, String password);

    abstract boolean startCamera(NetworkInterface networkInterface, String password);

    abstract boolean stopCamera(NetworkInterface networkInterface, String password);

    abstract boolean setWideFoV(NetworkInterface networkInterface, String password);

    abstract boolean setMediumFoV(NetworkInterface networkInterface, String password);

    abstract boolean setNarrowFoV(NetworkInterface networkInterface, String password);

    abstract boolean setVolumeOff(NetworkInterface networkInterface, String password);

    abstract boolean setVolume70(NetworkInterface networkInterface, String password);

    abstract boolean setVolume100(NetworkInterface networkInterface, String password);

    abstract boolean setContinuousShotSingle(NetworkInterface networkInterface, String password);

    abstract boolean setContinuousShot3SPS(NetworkInterface networkInterface, String password);

    abstract boolean setContinuousShot5SPS(NetworkInterface networkInterface, String password);

    abstract boolean setContinuousShot10SPS(NetworkInterface networkInterface, String password);

    abstract boolean setProTuneOn(NetworkInterface networkInterface, String password);

    abstract boolean setProTuneOff(NetworkInterface networkInterface, String password);

    abstract boolean turnGoProOn(NetworkInterface networkInterface, String password);

    abstract boolean turnGoProOff(NetworkInterface networkInterface, String password);

    abstract boolean setAutoOffNever(NetworkInterface networkInterface, String password);

    abstract boolean setAutoOff60s(NetworkInterface networkInterface, String password);

    abstract boolean setAutoOff120s(NetworkInterface networkInterface, String password);

    abstract boolean setAutoOff300s(NetworkInterface networkInterface, String password);

    abstract boolean setDefaultModeVideo(NetworkInterface networkInterface, String password);

    abstract boolean setDefaultModePhoto(NetworkInterface networkInterface, String password);

    abstract boolean setDefaultModeBurst(NetworkInterface networkInterface, String password);

    abstract boolean setDefaultModeTimelapse(NetworkInterface networkInterface, String password);

    abstract boolean deleteLast(NetworkInterface networkInterface, String password) throws IOException;

    abstract boolean deleteAll(NetworkInterface networkInterface, String password) throws IOException;

    abstract boolean deleteLast(String password);

    abstract boolean deleteAll(String password);

    public abstract ArrayList<String> getFileListSingleShot(String URL);

    public abstract ArrayList<String> getFileListVideo(String URL);

}
