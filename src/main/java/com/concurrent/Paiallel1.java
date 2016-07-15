package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Paiallel1 {
    public static Integer[] segArray(int start, int end, int[] array) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = start; i < end; i++) {
            result.add(array[i]);
        }
 
        Integer[] ary = result.toArray(new Integer[result.size()]);
 
        return ary;
    }
 
    public static int getSum(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result += array[i];
        }
        return result;
    }
 
    public static void main(String[] args) throws InterruptedException,
            ExecutionException {
        
        ExecutorService exec = Executors.newCachedThreadPool();//Executors.newFixedThreadPool(10);
        int[] array = { 300, 800, 89, 390, 892, 9384, 909, 1, 343, 5839, 939,
                43, 355, 323, 32, 55, 3, 3, 43, 5, 5, 45, 555, 554, 554, 555,
                545, 555, 553, 35, 2322, 332, 3232, 433, 344, 524, 245, 524,
                6565, 526 };
 
        List<FutureTask<Integer>> tasks = new ArrayList<FutureTask<Integer>>();
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            int incre = array.length / 10;
            int start = i * incre;
            int end = (i + 1) * incre;
            if (end > array.length)
                end = array.length; 	
            Integer[] prt = segArray(start, end, array);
 
            TaskWithResult calbTask = new TaskWithResult(prt);
            FutureTask<Integer> task = new FutureTask<Integer>(calbTask);
            tasks.add(task);
            if (!exec.isShutdown()) {
                exec.submit(task);
            }
        }
 
        int result = 0;
        for (FutureTask<Integer> task : tasks) {
            int partRst = task.get();
            task.toString();
            result += partRst;
        }
        
        long end1 = System.currentTimeMillis();
        System.out.println("并行计算耗时：" + (end1 - start1));
        System.out.println("并行计算的结果：" + result);
         
        long start2 = System.currentTimeMillis();
        TaskWithResult calbTask = new TaskWithResult(segArray(0, array.length, array));
        FutureTask<Integer> task = new FutureTask<Integer>(calbTask);
        exec.submit(task); 
        long end2 = System.currentTimeMillis();
        exec.shutdown();
        System.out.println("线性计算耗时：" + (end2 - start2));
        System.out.println("单独计算的结果：" + task.get());
    }
 
}