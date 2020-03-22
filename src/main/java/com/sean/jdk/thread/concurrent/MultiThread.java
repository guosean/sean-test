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
            System.out.println(mcc.compute("sss"));
            System.out.println(mcc.compute("abcd"));
        } catch (ExecutionException e) {ø
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class MultiConditionComupute {

    List<Condition> conditions = Arrays.asList(s -> !s.isEmpty(), s -> s.startsWith("a"), s -> s.contains("cd"));

    ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());


    public boolean compute(String str) throws ExecutionException, InterruptedException {
        boolean result = false;
        List<ListenableFuture<Boolean>> futures = Lists.newLinkedList();

        for (Condition condition : conditions) {
            futures.add(executorService.submit(new ConditionTask(condition, str)));
        }

        List<Boolean> futureResult = Futures.allAsList(futures).get();
        result = !futureResult.stream().anyMatch(b -> b == false);

        return result;
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
