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
    private static final String sqlInsert = "INSERT INTO TB_BITACORA (PAQUETE, TRAMA, TEXTO, INTENTO, IP_ORIGEN, ESTACION, ESTADO, FECHA_CREADO, FECHA_ACTUALIZADO, FECHA_CARGADO ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

    public Long insertTrama(Registry r) {
        Long idGenerate=0L;
        Connection conn = dbConnector.getEstadoBD().getConection();
        try (PreparedStatement pstm = conn.prepareStatement(sqlInsert, new String[]{"ID"})) {
            pstm.setInt(1, r.getIdpaquete());
            pstm.setInt(2, r.getIdtrama());
            pstm.setString(3, r.getTexto());;
            pstm.setInt(4, r.getIntento());
            pstm.setString(5, r.getIpOrigen());
            pstm.setString(6, r.getEstacion());
            pstm.setInt(7, r.getEstado());
            pstm.setString(8, r.getFechaCreado());
            pstm.setString(9, r.getFechaActualizado());
            pstm.setString(10, r.getFechaCargado());
            pstm.executeUpdate();
            try (ResultSet idGenerated = pstm.getGeneratedKeys()) {
                if (idGenerated.next()) {
                    idGenerate = idGenerated.getLong(1);
                }
                idGenerated.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       return idGenerate;
    }

    private static final String sqlSelectTrama = "SELECT * FROM TB_BITACORA WHERE ESTADO<=0;";

    public List<Registry> getListRegistros() {
        List< Registry> list = new ArrayList<>();
        try (PreparedStatement pstm = dbconection.prepareStatement(sqlSelectTrama)) {
            try (ResultSet rset = pstm.executeQuery()) {
                while (rset.next()) {
                    Registry r = new Registry();
                    r.setId(rset.getInt("ID"));
                    r.setEstado(0);
                    //r.getFechaActualizado(rset.getString("FECHA_ACTUALIZADO"));
                    r.setFechaCargado(rset.getString("FECHA_CARGADO"));
                    r.setTexto(rset.getString("TEXTO"));
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
