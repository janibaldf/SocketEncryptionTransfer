/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socketclient;

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
    Socket socket=null;
    public SocketClient() throws IOException{
          int serverPort = 6987;
              //InetAddress host = InetAddress.getByName("172.16.169.16");
              InetAddress host = InetAddress.getByName("localhost");
           
              
                 socket = new Socket(host,serverPort);
    }
      public void run(int id,String msg) {
	
        
            
              try {
               
                   PrintWriter toServer =
                      new PrintWriter(socket.getOutputStream(),true);
                       
              BufferedReader fromServer =
                      new BufferedReader(
                              new InputStreamReader(socket.getInputStream()));
              toServer.println(msg);
              
              String line = fromServer.readLine();
              System.out.println(line);
              
                  
              } catch (IOException ex) {
                  Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
              }
             
       
        
	}
  }
