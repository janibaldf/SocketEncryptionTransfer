/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbconection;

import com.bean.Registry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author janibaldf
 */
public class dbManager {

    Connection dbconection = dbConnector.getEstadoBD().getConection();
    private static final String sqlInsert = "INSERT INTO TB_BITACORA (PAQUETE, TRAMA, TEXTO, INTENTO, ESTADO, FECHA_CARGADO, FECHA_ACTUALIZADO) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
private static final String sqlInsertPaquete = "INSERT INTO TB_PAQUETE (FECHA_CARGADO, FECHA_ACTUALIZADO, ESTADO)VALUES(?,? ,0);";

    public void insertTrama(Registry r) {
        Connection conn = dbConnector.getEstadoBD().getConection();
        try (PreparedStatement pstm = conn.prepareStatement(sqlInsert)) {
            pstm.setInt(1, r.getIdpaquete());
            pstm.setInt(2, r.getIdtrama());
            pstm.setString(3, r.getTexto());;
            pstm.setInt(4, 0);
            pstm.setInt(5, 0);
            pstm.setString(6, r.getFechaCargado());
            pstm.setString(7, r.getFechaActualizo());
           pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
        public long getSiguietePaquete() {
           
        Connection conn = dbConnector.getEstadoBD().getConection();
        Date date = new Date();
        long id=0L;
        try ( PreparedStatement pstm = conn.prepareStatement(sqlInsertPaquete, new String[]{"ID"})) {
            pstm.setString(1, date.toString());
         pstm.setString(2, date.toString());
             
            pstm.executeUpdate();
            try (ResultSet idGenerated = pstm.getGeneratedKeys()) {
                if (idGenerated.next()) {
                    id = idGenerated.getLong(1);
                }
                idGenerated.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
        
        
        
private static final String sqlSelectTrama= "SELECT * FROM TB_BITACORA WHERE ESTADO<=0;";
        
       public List <Registry> getListRegistros(){
          List< Registry> list = new ArrayList<>();
           try (PreparedStatement pstm = dbconection.prepareStatement(sqlSelectTrama)) {
            try (ResultSet rset = pstm.executeQuery()) {
                while (rset.next()) {
                   Registry r= new Registry();
                   r.setId(rset.getInt("ID"));
                   r.setEstado(rset.getInt("ESTADO"));
                   r.setFechaActualizo(rset.getString("FECHA_ACTUALIZADO"));
                    r.setFechaCargado(rset.getString("FECHA_CARGADO"));
                     r.setTexto(rset.getString("TEXTO"));
                     r.setIdpaquete(rset.getInt("PAQUETE"));
                      r.setIdtrama(rset.getInt("TRAMA"));
                      r.setIntento(rset.getInt("INTENTO"));
                     list.add(r);
                }
                rset.close();
                pstm.close();
             }
               
        } catch (SQLException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           return list;
       
}
}



