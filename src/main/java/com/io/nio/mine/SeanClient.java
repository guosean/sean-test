package com.io.nio.mine;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by guozhenbin on 16/8/3.
 */
public class SeanClient {

    EventLoopGroup group = new NioEventLoopGroup();

    static String host = "127.0.0.1";
    static int port = 8081;

    public static void main(String[] args) {
        SeanClient client = new SeanClient();
        client.connect(host,port);
    }

    private void connect(final String host,final int port){
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true);

            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new SeanClientHandler());
                }
            });

            ChannelFuture future = bootstrap.connect(host,port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

    private class SeanClientHandler extends ChannelInboundHandlerAdapter {

        private ByteBuf msg;

        public SeanClientHandler(){
            byte[] req = "QUERY TIME ORDER".getBytes();
            msg = Unpooled.buffer(req.length);
            msg.writeBytes(req);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(msg);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf)msg;
            byte[] bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes);
            String content = new String(bytes,"UTF-8");
            System.out.println("now is:"+content);

            ctx.writeAndFlush(msg);
        }
    }
}
