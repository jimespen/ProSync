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
    String command;
    Socket socket;
    int delay;
    
    public Connection(String nifName, int delay) throws IOException, UnknownHostException {
        socket = new Socket();
        NetworkInterface nif = NetworkInterface.getByName(nifName);
        Enumeration<InetAddress> nifAddress = nif.getInetAddresses();
        socket.bind(new InetSocketAddress(nifAddress.nextElement(),0));
        socket.connect(new InetSocketAddress("10.5.5.9",80));
        out = new PrintWriter(socket.getOutputStream(), true);
        this.delay = delay;
    }
    

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println("GET /camera/SH?t=testtest&p=%01 HTTP/1.1");
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
}
