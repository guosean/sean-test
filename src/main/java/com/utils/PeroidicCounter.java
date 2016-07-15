package com.utils;

public class PeroidicCounter {

    public static enum Peroidic {
        SECOND(1000L), MINITE(1000L * 60), HOUR(1000L * 60 * 60);
        private final long millis;

        private Peroidic(long millis) {
            this.millis = millis;
        }

        public long getCurrentKey() {
            return System.currentTimeMillis() / millis;
        }

        public long getMillis() {
            return millis;
        }
    }

    protected final Peroidic peroidic;

    public PeroidicCounter(Peroidic peroidic) {
        this.peroidic = peroidic;
    }

    private volatile long count = 0;

    private volatile long peroidicKey = 0;

    public synchronized long increase() {
        return increaseBy(1);
    }

    public synchronized long increaseBy(long step) {
        long currentKey = peroidic.getCurrentKey();
        if (currentKey != peroidicKey) {
            peroidicKey = currentKey;
            count = 0;
        }
        count += step;
        return count;
    }
}
