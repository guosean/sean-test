package com.io.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;

/**
 * Created by guozhenbin on 16/8/3.
 */
public class TestNetty {

    public static void main(String[] args) {
        final byte[] CONTENT = "content".getBytes();
        int loop = 3000000;
        long startTime = System.currentTimeMillis();
        ByteBuf poolBuffer = null;
        for(int i=0; i<loop; i++){
            poolBuffer = PooledByteBufAllocator.DEFAULT.directBuffer(1024);
            poolBuffer.writeBytes(CONTENT);
            poolBuffer.release();
        }
        System.out.println("poolbuffer:"+(System.currentTimeMillis()-startTime));

        startTime = System.currentTimeMillis();
        for(int i=0; i<loop; i++){
            poolBuffer = UnpooledByteBufAllocator.DEFAULT.directBuffer(1024);
            poolBuffer.writeBytes(CONTENT);
            poolBuffer.release();
        }
        System.out.println("unpoolbuffer:"+(System.currentTimeMillis()-startTime));
    }

}
