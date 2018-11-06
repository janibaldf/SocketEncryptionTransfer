
package com.serversocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


class ServerInitializer extends ChannelInitializer<SocketChannel> {


    /**
     * Metodo: initChannel
     * Inicializa la configuracion del socket que esta escuchando.
     * @param ch: el Channel que se crea para los hilos.
     * @throws Exception : cualquier error no contemplado
     */
    
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new ServerHandler());

    }
}
