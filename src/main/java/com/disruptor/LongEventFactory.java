package com.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by guozhenbin on 2016/10/27.
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
