/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socketclient;

import com.dbconection.dbManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author janibaldf
 */
public class SocketClient {

    Socket socket = null;

    public SocketClient(String serverIP , String port) throws IOException {
        int serverPort = Integer.valueOf(port);
                
        //InetAddress host = InetAddress.getByName("172.16.169.16");
        InetAddress host = InetAddress.getByName(serverIP);

        socket = new Socket(host, serverPort);
        
    }
 dbManager dbmanager = new dbManager();
    public void run(int id, String msg) throws InterruptedException {

        try {
Thread.sleep(100);
            PrintWriter toServer
                    = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader fromServer
                    = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            toServer.println(msg);

            String line = fromServer.readLine();
            if ( !line.equals("0")){
            dbmanager.updateEstado(id, 2);
            dbmanager.updateCentral(id, Integer.valueOf(line));
            System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
