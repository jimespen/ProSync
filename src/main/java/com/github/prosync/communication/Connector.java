package com.github.prosync.communication;

/**
 * Created by huseby on 10/3/14.
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;

public class Connector {

    /**
     * HTML file retriver, retrives a specified HTML from a URL
     *
     * @param url
     * @return A string containing the HTML file
     * @throws IOException
     */
    public String getHTMLFile(URL url) throws IOException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            String string = "";
            while ((inputLine = in.readLine()) != null) {
                string += inputLine;
            }

            in.close();

            return string;
        } catch (Exception e) {
            System.out.println("Exception in retriveHTMLFile(URL url)");
        }

        return null;
    }

    /**
     * HTTP File retriever, used to downloading files from GoPro Cameras
     *
     * @param url  Example: new URL("http://10.5.5.9:8080/DCIM/105GOPRO/File.mp4")
     * @param file Example: new File("path/to/File.mp4")
     * @return true if successful, false if not
     */
    public boolean getFileHTTP(URL url, File file) {
        URLConnection connection;
        int i;
        try {
            connection = url.openConnection();
            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file.getPath()));

            while ((i = bis.read()) != -1) {
                bos.write(i);
            }
            bos.flush();
            bos.close();
            bis.close();
        } catch (MalformedInputException malformedInputException) {
            malformedInputException.printStackTrace();
            return false;
        } catch (IOException ioException) {
            return false;
        }
        System.out.println(true);
        return true;
    }

    // HTTP GET request

    /**
     * GET Request method for sending commands to GoPro Cameras
     * Only works if each cam got a unique IP, or if you only want to connect to one cam
     * Use SocketConnection if each cam don't have a unique IP.
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
