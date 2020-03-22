package com.sean.reactive;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.stream.Stream;

/**
 * TODO description
 *
 * @author guozhenbinø
 * @date 2020/2/18
 */
public class ReactorTest {

    @Test
    public void testBackpressure() {
        Flux<Integer> longFlux = Flux.range(1,100);
        longFlux = longFlux.onBackpressureDrop(t -> {
            System.out.println("droped:"+t);
        });
        longFlux.map(t -> {
            return t*2;
        });
        longFlux.subscribe(new Subscriber<Integer>() {
            Subscription subscription;
            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(Integer aLong) {
                System.out.println("val:"+aLong);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });

    }

    public static void main(String[] args) {
        Flux.range(0,10).doOnNext(integer -> {
            System.out.println("onNext:"+integer);
        }).bufferUntil(integer -> {
            return integer > 6;
        }).doOnNext(list -> {
            System.out.println(list);
        }).subscribe();

        Mono.fromCallable( () -> System.currentTimeMillis() )
                .repeat()
                .publishOn(Schedulers.single())
                .log("foo.bar")
                .map(t->{
                    return t*2;
                })
                .flatMap(time ->
                                Mono.fromCallable(() -> { Thread.sleep(1000); return time; })
                                        .subscribeOn(Schedulers.parallel())
                        , 8) //maxConcurrency 8
                .subscribe();
    }

}
