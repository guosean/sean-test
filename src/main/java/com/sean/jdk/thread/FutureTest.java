package com.sean.jdk.thread;

import java.util.concurrent.*;

/**
 * Created by guozhenbin on 16/8/9.
 */
public class FutureTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("子线程在进行计算");
                Thread.sleep(3000);
                int sum = 0;
                for(int i=0;i<100;i++)
                    sum += i;
                return sum;
            }
        });
        service.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果"+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException f){
            f.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }

}
