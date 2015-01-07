package com.github.prosync.communication;

/**
 * Created by oystein on 16.12.2014.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Connection, designed to use sockets to connect with GoPRO cameras
 */
public class SocketConnection {

    private PrintWriter pw;
    private Socket socket;
    private NetworkInterface ni;

    /**
     * SocketConnection (NetworkInterface, String, int)
     * Takes in a network interface to send a package trough, and a IP and port to send a package to
     * Creates a socket connection to the to the IP, allowing packages trough and executed.
     *
     * @param ni   NetworkInterface of Host computer to create a socket connection with
     * @param IP   IP of the remote Host
     * @param port Port of the remote host.
     * @throws IOException
     */
    public SocketConnection(NetworkInterface ni, String IP, int port) throws IOException {
        this.ni = ni;
        socket = new Socket();
        Enumeration<InetAddress> nifAddress = ni.getInetAddresses();
        socket.bind(new InetSocketAddress(nifAddress.nextElement(), 0));
        socket.connect(new InetSocketAddress(IP, port));
        pw = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * sendGetCommand(String command), sends a getCommand trough the specified NetworkInterface and to the given IP.
     * Designed to use a String command to GoProCameras in order for them to be executed.
     * Command Format "GET /bacpac/"<Camera Command>?=t<Wifi Password>&p=%<Camera Command Number> HTTP/1.1"
     * Command Example: "GET /bacpac/SH?t=testtest&p=%01 HTTP/1.1"
     *
     * @param command GetCommand to send to send to cam, see example.
     */
    public void sendGetCommand(String command) {
        pw.println(command);
        pw.println("");
        pw.flush();
    }

    /**
     * close() Method to close a connection to a
     */
    public void close() {
        try {
            socket.close();
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

