package com.concurrent;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.TimeUnit;

/**
 * Created by guozhenbin on 16/7/13.
 */
public class TestValidate {

    private static boolean stop;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(stop);
       Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("thread running");
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        stop = true;
    }

}

