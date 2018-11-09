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
import com.bean.Registry;
import com.dbconection.dbManager;
import java.util.Date;

class ServerHandler extends SimpleChannelInboundHandler<String> {

    private String clientIP;
    private String clientHost;

    /**
     * Metodo: channelActive El metodo llamado cuando el hilo del socket es
     * llamado.
     *
     * @param ctx: contexto del hilo
     * @throws UnknownHostException: si el host no se puede identificar.
     */
    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws UnknownHostException {
        clientHost = "no identificado";
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        InetAddress inetaddress = socketAddress.getAddress();
        clientIP = inetaddress.getHostAddress();
        clientHost = inetaddress.getHostName();
    }

    /**
     * Metodo: channelRead0 Es el metodo que lee la trama de entrada y
     * desencadena los eventos para cada transaccion.
     *
     * @param ctx: contexto del hilo
     * @param msg: trama de entrada
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws InterruptedException {
        String response = null;
        String zonaHoraria = "GMT-6";
        LocalTime tiempoLlegada;
        Encryption encryption = new Encryption();
        LocalTime tiempoRespuesta = LocalTime.from(Instant.now().atZone(ZoneId.of(zonaHoraria)));
        tiempoLlegada = LocalTime.from(Instant.now().atZone(ZoneId.of(zonaHoraria)));
        tiempoRespuesta = LocalTime.from(Instant.now().atZone(ZoneId.of(zonaHoraria)));
        String msgDes = null;
        try {
            msgDes = encryption.desencryptionText(msg);
            // msgDes = msg;
        } catch (Exception ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        Registry reg = new Registry();
        dbManager dbmanager = new dbManager();
        String[] s = msgDes.split("\\$");
        reg.setIdpaquete(Integer.valueOf(s[0]));
        reg.setIntento(Integer.valueOf(s[1]));
        reg.setIdtrama(Integer.valueOf(s[2]));
        reg.setTexto(s[3]);
        reg.setEstado(Integer.valueOf(s[4]));
        Date date = new Date();
        reg.setFechaCargado(s[5]);
        reg.setFechaActualizado(s[6]);
        reg.setFechaCreado(date.toString());
        reg.setIpOrigen(clientIP);
        reg.setEstacion(s[7]);
        long idRegistro= dbmanager.insertTrama(reg);

        ctx.writeAndFlush(idRegistro+"\n");
        System.out.println("Procesada:"+idRegistro +":" + msgDes+"\n");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, cause.toString(), cause.toString());

        ctx.close();
    }
}
