package com.io.nio.mine;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Date;

/**
 * Created by guozhenbin on 16/8/3.
 */
public class SeanNettyServer {

    ServerBootstrap serverBootstrap = new ServerBootstrap();

    EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup();
    EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();

    int port = 8081;

    public static void main(String[] args) {
        SeanNettyServer seanServer = new SeanNettyServer();
        seanServer.initServer();

    }

    private void initServer(){

        try {
            serverBootstrap.group(bossEventLoopGroup,workerEventLoopGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024).childHandler(new WorkerChannelHandler());
            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossEventLoopGroup.shutdownGracefully();
            workerEventLoopGroup.shutdownGracefully();
        }

    }

}
class WorkerChannelHandler extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new SeanWorkHandler());
    }
}
class SeanWorkHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);

        String content = new String(bytes,"UTF-8");
        System.out.println("rev content:"+content);
        String result = "result:"+new Date().toString();
        ByteBuf rep = Unpooled.copiedBuffer(result.getBytes());
        ctx.write(rep);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}

