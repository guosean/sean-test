package com.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * TODO description
 *
 * @author guozhenbin
 */
public class TestForkjoin {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        CountTask task = new CountTask(1, 5);
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class CountTask extends RecursiveTask<Integer> {

        private int start = 0;
        private int end = 0;

        private static final int THRESHOLD = 2;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            if (end < start) {
                return 0;
            }

            if (end - start <= THRESHOLD) {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                int middle = (start + end) / 2;
                CountTask lowTask = new CountTask(start, middle);
                CountTask highTask = new CountTask(middle + 1, end);
                lowTask.fork();
                highTask.fork();
                int lowResult = lowTask.join();
                int highResult = highTask.join();
                sum = lowResult + highResult;
            }

            return sum;
        }
    }
}
