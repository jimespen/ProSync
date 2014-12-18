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
public class ConnectionExprimental {

    private PrintWriter pw;
    private Socket socket;
    private NetworkInterface ni;

    public ConnectionExprimental(NetworkInterface ni, String IP, int port) throws IOException {
        this.ni = ni;
        socket = new Socket();
        Enumeration<InetAddress> nifAddress = ni.getInetAddresses();
        socket.bind(new InetSocketAddress(nifAddress.nextElement(), 0));
        socket.connect(new InetSocketAddress(IP, port));
        pw = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendGetCommand(String command) {
        pw.println(command);
        pw.println("");
        pw.flush();
    }

    public void close() {
        try {
            socket.close();
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

