package com.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by guozhenbin on 2016/10/27.
 */
public class LongEventMain {

    public static void main(String[] args) throws Exception {
        Executor executor = Executors.newFixedThreadPool(10);
        LongEventFactory factory = new LongEventFactory();
        int bufferSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory,bufferSize,executor);
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
//        ByteBuffer bb = ByteBuffer.allocate(8);
        for(long l=0; true; l++){
//            bb.putLong(l);
            producer.onData(l);
            Thread.sleep(500);
        }
    }

}
