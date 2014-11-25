/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.communication;

/**
 *
 * @author Ruben
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection implements Runnable {

    PrintWriter out;
    String command = "PW?t=testtest&p=%01";
    Socket socket;
    int delay;
    Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
    String IP = "10.5.5.9";

    public Connection(String nifName, int delay) throws IOException, UnknownHostException {
        socket = new Socket();
        NetworkInterface nif = NetworkInterface.getByName(nifName);
        Enumeration<InetAddress> nifAddress = nif.getInetAddresses();
        socket.bind(new InetSocketAddress(nifAddress.nextElement(), 0));
        socket.connect(new InetSocketAddress("10.5.5.9", 80));
        out = new PrintWriter(socket.getOutputStream(), true);
        this.delay = delay;

    }

    public Connection(String IP) throws IOException, UnknownHostException {
        this.delay = 0;
        this.IP = IP;
    }

    @Override
    public void run() {
        out.println("GET /camera/" + command + " HTTP/1.1");
        out.println("");
        out.flush();
        System.out.println("Thread finished");
    }

    public String bind(String otherNif) throws InterruptedException {
        NetworkInterface nextElement = null;
        while (interfaces.hasMoreElements()) {
            nextElement = interfaces.nextElement();
            if (!nextElement.getName().equals(otherNif)) {
                try {
                    Enumeration<InetAddress> nifAddress = nextElement.getInetAddresses();
                    this.socket = new Socket();
                    try {
                        socket.bind(new InetSocketAddress(nifAddress.nextElement(), 0));
                        socket.connect(new InetSocketAddress(IP, 80));
                        System.out.println("****Network found****");
                        System.out.println(nextElement.getName());
                        Thread.sleep(2000);
                        return nextElement.getName();
                    } catch (SocketException se) {
                        se.printStackTrace(System.err);
                    }
                } catch (UnknownHostException uhe) {
                    uhe.printStackTrace(System.err);
                } catch (IOException ioe) {
                    ioe.printStackTrace(System.err);
                } catch (NoSuchElementException nee) {
                    nee.printStackTrace(System.err);
                }
            }
        }
        return "";
    }

    public void setCommand(String cmd, String tall) {
        this.command = cmd + "?t=testtest&p=%" + tall;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        String command = "SH?t=testtest&p=%01";
        for (int i = 0; i < 100; i++) {

        }
        Connection con = new Connection("wlan3", 0);
        Connection con2 = new Connection("wlan0", 0);
        con.command = command;
        con2.command = command;
        for (int i = 0; i < 100; i++) {
            con.run();
            con2.run();
            Thread.sleep(2000);
        }
    }

}
