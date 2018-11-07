/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbconection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author janibaldf
 */
public class dbConnector {

    String url = "/home/janibaldf/Desarrollo/ProyectoRede1/DB/CentralDB.db";
    Connection connect;

    private static dbConnector instance;

    private  dbConnector() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
            if (connect != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
        }
    }

    public static synchronized dbConnector getEstadoBD() {
        if (instance == null) {
            instance = new dbConnector();
        }
        return instance;

    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(dbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public Connection getConection() {
        return connect;
    }

}
