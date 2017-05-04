package com.io.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by guozhenbin on 16/7/8.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf msg;

    public TimeClientHandler(){
        byte[] req = "QUERY TIME ORDER".getBytes();
        msg = Unpooled.buffer(req.length);
        msg.writeBytes(req);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.printf("Now is: "+ body);
        ctx.writeAndFlush(msg);
    }
}
