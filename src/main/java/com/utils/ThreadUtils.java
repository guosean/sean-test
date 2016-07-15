package com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadUtils.class);

    public static void sleep(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public static void sleepQuite(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            LOGGER.error("sleep was interrupted", e);
        }
    }
}
