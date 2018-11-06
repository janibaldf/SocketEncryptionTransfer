
package com.serversocket;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.net.InetAddress;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.net.InetSocketAddress;
import com.utility.Encryption;

class ServerHandler extends SimpleChannelInboundHandler<String> {

    private String clientIP;
    private String clientHost;

    /**
     * Metodo: channelActive
     * El metodo llamado cuando el hilo del socket es llamado.
     * @param ctx: contexto del hilo
     * @throws UnknownHostException: si el host no se puede identificar. 
     */
    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws UnknownHostException {
        clientHost = "no identificado";
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        InetAddress inetaddress = socketAddress.getAddress();
        clientIP =  inetaddress.getHostAddress();
        clientHost = inetaddress.getHostName();
    }
    
    /**
     * Metodo: channelRead0
     * Es el metodo que lee la trama de entrada y desencadena los eventos para cada transaccion.
     * @param ctx: contexto del hilo
     * @param msg: trama de entrada
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws InterruptedException {
        String response=null;
        String zonaHoraria="GMT-6";
        LocalTime tiempoLlegada;
        Encryption encryption=  new Encryption();
                LocalTime tiempoRespuesta=LocalTime.from(Instant.now().atZone(ZoneId.of(zonaHoraria)));
                tiempoLlegada = LocalTime.from(Instant.now().atZone(ZoneId.of(zonaHoraria)));
                  tiempoRespuesta = LocalTime.from(Instant.now().atZone(ZoneId.of(zonaHoraria)));
                 String msgDes=null;
        try {
            //msgDes = encryption.desencryptionText(msg);
               msgDes = msg;
        } catch (Exception ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
                ctx.writeAndFlush("Llegada "+tiempoLlegada +": mensaje "+ msgDes+"estado: recibida\n");
                System.out.println("Procesada:"+msgDes);
           
        if (msg.trim().length() > 0) {
            try {
                Connection localConnection;
              //Conexion a base de datos
              // todas la operaciones
           
               
                //response=val.getResultTransaccion();
              
                 
            } catch (Exception e) {
               // Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, e.getMessage(), e);
               // logger.recordLog("Error en channelRead0 \n"+this.getClass().getName()+"\n"+e.getSQLState()+"\n");
            }
        } else {
            ctx.writeAndFlush(Instant.now() + " Error : " + msg);
            //logger.recordLog("Error en lectura de Socket\n"+this.getClass().getName()+"\n"+msg+"\n");
        }
          // LoggerTransac log =LoggerTransac.getInstance("/");
       //log.recordLog("[Entrada: "+tiempoLlegada+"]"+msg+"> origen "+clientIP+"/"+clientHost +
         //            "\n[Salida: "+tiempoRespuesta+"]"+response+
           //           "\n[Demora: " + ChronoUnit.MILLIS.between(tiempoLlegada, tiempoRespuesta) + "]\n");
    }
    
    /**
     * Metodo: exceptionCaughta
     * Si en algun momento de la lectura en el buffer da error, se intancia este metodo.
     * @param ctx: contexto del hilo
     * @param cause : la causa del error.
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, cause.toString(), cause.toString());
          
        //logger.recordLog("Error en lectura de Socket exceptionCaught\n"+this.getClass().getName()+"\n"+cause.toString()+"\n");
        ctx.close();
    }
}
