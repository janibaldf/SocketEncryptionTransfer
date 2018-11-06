
package com.serversocket;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSocketMain {

    public void startSocketServer(int port) {
        
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ServerInitializer());
            try {
                bootstrap.bind(port).sync().channel().closeFuture().sync();
                System.out.print("Servicio iniciado en "+ port);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerSocketMain.class.getName()).log(Level.INFO, null, ex);
                Thread.currentThread().interrupt();
              //  LoggerTransac logger =LoggerTransac.getInstance("/");
              //logger.recordLog("Error al iniciar el Server Socket\n"+this.getClass().getName()+"\n"+ex.toString()+"\n");
            
            }
            
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
