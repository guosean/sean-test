package com.sean.jdk.thread.concurrent;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * TODO description
 *
 * @author guozhenbin
 * @date 2020/3/22
 */
public class MultiThread {

    @Test
    public void testMultiThread() {

        MultiConditionComupute mcc = new MultiConditionComupute();
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                mcc.compute("sss");
                mcc.compute("abcd");
            }

            System.out.println(System.currentTimeMillis() - start);

            start = System.currentTimeMillis();
            for (int j = 0; j < 1000; j++) {
                mcc.computeSync("sss");
                mcc.computeSync("abcd");
            }

            System.out.println(System.currentTimeMillis() - start);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class MultiConditionComupute {

    List<Condition> conditions = Arrays.asList(s -> {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return !s.isEmpty();
    }, s -> {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s.startsWith("a");
    }, s -> {
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s.contains("cd");
    });

    ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(3));

    public boolean computeSync(String str) {
        List<Boolean> list = Lists.newArrayListWithCapacity(conditions.size());

        for (Condition condition : conditions) {
            list.add(condition.test(str));
        }

        return !list.stream().anyMatch(b -> b == false);
    }

    public boolean compute(String str) throws ExecutionException, InterruptedException {
        List<ListenableFuture<Boolean>> futures = Lists.newLinkedList();

        conditions.stream().forEach(condition -> futures.add(executorService.submit(new ConditionTask(condition, str))));

        List<Boolean> futureResult = Futures.allAsList(futures).get();

        return !futureResult.stream().anyMatch(b -> b == false);
    }
}

interface Condition {

    boolean test(String str);

}

class ConditionTask implements Callable<Boolean> {

    Condition condition;

    String value;

    public ConditionTask(Condition condition, String value) {
        this.condition = condition;
        this.value = value;
    }

    @Override
    public Boolean call() throws Exception {
        return null == condition || condition.test(value);
    }

}
