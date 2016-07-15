package com.guava;

import com.google.common.util.concurrent.AbstractFuture;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by guozhenbin on 16/7/13.
 */
public class TestFuture {

    private static MyFuture future = new MyFuture();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("wait get...");
                    System.out.println(future.get(10, TimeUnit.SECONDS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("set date");
                future.set(false);
            }
        }).start();

        System.out.println("end");
    }
}

class MyFuture extends AbstractFuture<Boolean>{
    @Override
    protected boolean set(@Nullable Boolean value) {
        return super.set(value);
    }
}
