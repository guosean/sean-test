package com.disruptor;

import com.lmax.disruptor.RingBuffer;

/**
 * Created by guozhenbin on 2016/10/27.
 */
public class LongEventProducer {

    private RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(Long lg) {
        long sequence = ringBuffer.next();
        try {
            LongEvent longEvent = ringBuffer.get(sequence);
            longEvent.setValue(lg);
        } finally {
            ringBuffer.publish(sequence);
        }
    }

}
