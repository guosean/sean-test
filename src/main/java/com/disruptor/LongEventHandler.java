package com.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * Created by guozhenbin on 2016/10/27.
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        Thread.sleep(2000);
        System.out.println("thread "+ Thread.currentThread().getName() +" event:"+longEvent);
    }
}
