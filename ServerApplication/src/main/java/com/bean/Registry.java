
package com.bean;

public class Registry {
   private int id;
   private int idpaquete;
   private int idtrama;
   private String texto;
   private int intento;
   private  String IpOrigen;
   private String estacion;
   private int estado;
   private String fechaCreado;
   private String fechaActualizado;
   private String fechaCargado;  

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

    public int getIntento() {
        return intento;
    }

    public void setIntento(int intento) {
        this.intento = intento;
    }

    public String getIpOrigen() {
        return IpOrigen;
    }

    public void setIpOrigen(String IpOrigen) {
        this.IpOrigen = IpOrigen;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(String fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public String getFechaActualizado() {
        return fechaActualizado;
    }

    public void setFechaActualizado(String fechaActualizado) {
        this.fechaActualizado = fechaActualizado;
    }

    public String getFechaCargado() {
        return fechaCargado;
    }

    public void setFechaCargado(String fechaCargado) {
        this.fechaCargado = fechaCargado;
    }

    @Override
    public String toString() {
        return "Registry{" + "id=" + id + ", idpaquete=" + idpaquete + ", idtrama=" + idtrama + ", texto=" + texto + ", intento=" + intento + ", IpOrigen=" + IpOrigen + ", estacion=" + estacion + ", estado=" + estado + ", fechaCreado=" + fechaCreado + ", fechaActualizado=" + fechaActualizado + ", fechaCargado=" + fechaCargado + '}';
    }
           
   
}
