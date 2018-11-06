package com.main;

import com.serversocket.ServerSocketMain;

public class ServiceSocketStart {
  /* Inicia Servidor Socket */
    public static void main(String[] args) {
     
        try {
            ServerSocketMain ss = new ServerSocketMain();
            ss.startSocketServer(6987);            
        } 
        catch (Exception ex) {
        
        }
    }

}
