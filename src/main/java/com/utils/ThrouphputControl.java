package com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 吞吐量控制
 * @author zhenbin.guo
 *
 */
public class ThrouphputControl extends PeroidicCounter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThrouphputControl.class);

    private volatile int threshold;
    private volatile long startTime;

    public ThrouphputControl(Peroidic peroidic, int threshold) {
        super(peroidic);
        this.threshold = threshold;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public synchronized long countAndHold() {
        return countAndHold(1);
    }

    public synchronized long countAndHold(long step) {
        long currentCount = super.increaseBy(step);
        if (currentCount == step) {
            startTime = System.currentTimeMillis();
        }
        if (currentCount >= threshold) {
            long cost = System.currentTimeMillis() - startTime;
            long sleep = this.peroidic.getMillis() - cost;
            if (sleep < 0) {
                LOGGER.error("illegal sleep millis {}, maybe has a concurrent problem", sleep);
                return 0;
            }
            ThreadUtils.sleepQuite(sleep);
            return sleep;
        }
        return 0;
    }

}
