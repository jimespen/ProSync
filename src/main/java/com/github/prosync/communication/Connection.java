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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection implements Runnable{
    PrintWriter out;
    String command = "SH?t=testtest&p=%01";
    Socket socket;
    int delay;
    Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
    String IP;
    
    public Connection(String nifName, int delay) throws IOException, UnknownHostException {
        socket = new Socket();
        NetworkInterface nif = NetworkInterface.getByName(nifName);
        Enumeration<InetAddress> nifAddress = nif.getInetAddresses();
        socket.bind(new InetSocketAddress(nifAddress.nextElement(),0));
        socket.connect(new InetSocketAddress("10.5.5.9",80));
        out = new PrintWriter(socket.getOutputStream(), true);
        this.delay = delay;
        
    }
    
    public Connection(String cmd, String tall, String IP) throws IOException, UnknownHostException{
        this.delay = 0;
        this.command = cmd+"?t=testtest&p=%"+tall;
        this.IP = IP;
    }
    

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
            out = new PrintWriter(socket.getOutputStream(),true);
        } catch (InterruptedException ix) {
            ix.printStackTrace(System.err);
        } catch (IOException ioe){
            ioe.printStackTrace(System.err);
            return;
        }
        out.println("GET /camera/"+command+" HTTP/1.1");
        out.println("");
        out.flush();
        try{
        socket.close();
        } catch (IOException e){
            e.printStackTrace(System.err);
        } finally {
            System.out.println("Thread finished");
        }
    }
    
    public void bind(){
        NetworkInterface nextElement = null;
        while(interfaces.hasMoreElements()){
            nextElement = interfaces.nextElement();
            try{
                Enumeration<InetAddress> nifAddress = nextElement.getInetAddresses();
                this.socket = new Socket();
                socket.bind(new InetSocketAddress(nifAddress.nextElement(),0));
                socket.connect(new InetSocketAddress(IP,80));
                return;
            } catch (UnknownHostException uhe){
                uhe.printStackTrace(System.err);
            } catch (IOException ioe){
                ioe.printStackTrace(System.err);
            }
        }
    }
    
    
    public static void main(String[] args)throws UnknownHostException, IOException {
        Connection con1 = new Connection("wlan15", 0); //TestCam1
        Connection con2 = new Connection("wlan0", 0); //TestCam2
        Thread t1 = new Thread(con1);
        Thread t2 = new Thread(con2); 
        t1.start();
        t2.start();
        
    }
    
}
