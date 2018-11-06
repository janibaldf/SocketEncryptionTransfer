/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socketclient;

import com.bean.Registry;
import com.dbconection.dbManager;
import com.utility.Encryption;
import java.util.List;

/**
 *
 * @author janibaldf
 */
public class SocketDaemon extends Thread {

    public SocketDaemon() {
        setDaemon(true);
        start();
    }
    dbManager dbmanager = new dbManager();

    @Override
    public void run() {
        String prueba = "prueba";
        while (true) {
            try {
                sleep(10000); // 1 segundo (se mide en milisegundos)

            } catch (InterruptedException e) {
                e.getMessage();
            }
                SocketClient socket = new SocketClient();
               Encryption encryption= new Encryption();
            List<Registry> listRegistros = dbmanager.getListRegistros();
            for (Registry r : listRegistros) {
                System.out.println(r.toString());
                socket.run(encryption.encryptionText(r.toString()));
                
            }

        }
    }
}
