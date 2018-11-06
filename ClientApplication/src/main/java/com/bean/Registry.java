/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

/**
 *
 * @author janibaldf
 */
public class Registry {
  private int id;
  private int idpaquete;
  private int intento;
  private int idtrama;
  private String texto;
  private int estado;
  private String fechaCargado;
  private String fechaActualizo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdpaquete() {
        return idpaquete;
    }

    public void setIdpaquete(int idpaquete) {
        this.idpaquete = idpaquete;
    }

    public int getIntento() {
        return intento;
    }

    public void setIntento(int intento) {
        this.intento = intento;
    }

    public int getIdtrama() {
        return idtrama;
    }

    public void setIdtrama(int idtrama) {
        this.idtrama = idtrama;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaCargado() {
        return fechaCargado;
    }

    public void setFechaCargado(String fechaCargado) {
        this.fechaCargado = fechaCargado;
    }

    public String getFechaActualizo() {
        return fechaActualizo;
    }

    public void setFechaActualizo(String fechaActualizo) {
        this.fechaActualizo = fechaActualizo;
    }

    @Override
    public String toString() {
        return "registry{" + "id=" + id + ", idpaquete=" + idpaquete + ", intento=" + intento + ", idtrama=" + idtrama + ", texto=" + texto + ", estado=" + estado + ", fechaCargado=" + fechaCargado + ", fechaActualizo=" + fechaActualizo + '}';
    }
    

}
