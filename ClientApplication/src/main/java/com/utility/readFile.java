/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utility;
import com.bean.Registry;
import com.socketclient.SocketClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.dbconection.dbConnector;
import com.dbconection.dbManager;
import java.sql.Connection;
import java.util.Date;

/**
 *
 * @author rcastillo
 */
public class readFile {

    /**
     * @param args the command line arguments
     */
    
   static String Valor = "";
    
   public  static void muestraContenido(String archivo) throws FileNotFoundException, IOException {
     String cadena;
  
    
        FileReader f = new FileReader(archivo);
        dbManager dbmanager = new dbManager();
        Registry registry = new Registry();
       try (BufferedReader b = new BufferedReader(f)) {
           Encryption encryption = new Encryption();
           int i=0;
           int paquete=Integer.valueOf(String.valueOf(dbmanager.getSiguietePaquete()));
           while((cadena = b.readLine())!=null) {
               
               Valor = Valor + cadena + "\n";
               registry.setIdpaquete(paquete);
               registry.setIdtrama(i++);
               registry.setTexto(cadena);
               Date date = new Date();
               registry.setFechaActualizo(date.toString());
               registry.setFechaCargado(date.toString());
               dbmanager.insertTrama(registry);
           }
       }
    }
   


    
    public static String getCadena() {
        return Valor;
    }
    
}
