/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socketclient;

import com.bean.Registry;
import com.dbconection.dbManager;
import com.utility.Encryption;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author janibaldf
 */
public class SocketDaemon extends Thread {
String ip;
String port;
String estacion;
    public SocketDaemon(String ip , String port, String estacion) {
        this.estacion=estacion;
        this.ip=ip;
        this.port=port;
        setDaemon(true);
        start();
    }
    dbManager dbmanager = new dbManager();

    @Override
    public void run() {
    
        while (true) {
            try {
                sleep(100000); // 1 segundo (se mide en milisegundos)

            } catch (InterruptedException e) {
                e.getMessage();
            }
                SocketClient socket=null;
            try {
                socket = new SocketClient(ip, port);
            } catch (IOException ex) {
                Logger.getLogger(SocketDaemon.class.getName()).log(Level.SEVERE, null, ex);
            }
               Encryption encryption= new Encryption();
            List<Registry> listRegistros = dbmanager.getListRegistros();
            for (Registry r : listRegistros) {
                if (socket !=null){
                    r.setEstacion(estacion);
                    try {
                        Thread.sleep(500);
                        socket.run(r.getId(),encryption.encryptionText(r.toStringSplit()));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SocketDaemon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    dbmanager.updateIntento(r.getId());
                    dbmanager.updateEstado(r.getId(), -1);
                }
            }

        }
    }
}
