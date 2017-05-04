package com.disruptor;

/**
 * Created by guozhenbin on 2016/10/27.
 */
public class LongEvent {

    private long value;

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent["+value+"]";
    }
}
