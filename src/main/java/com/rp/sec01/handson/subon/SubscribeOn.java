package com.rp.sec01.handson.subon;

import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class SubscribeOn {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentSkipListSet<Object> set = new ConcurrentSkipListSet<>();
        int nThreads = 20;

        Flux<Object> flux = Flux.create(f -> {
                    for (int i = 0; i < 10; i++) {
                        f.next("Element " + i);
                        set.add(Util.getThread());
                        Util.sleepMillis(300);
                    }
                    f.complete();
                })
                .subscribeOn(Schedulers.boundedElastic());

        CountDownLatch latch = new CountDownLatch(nThreads);

        IntStream.range(0, nThreads).forEach(i -> flux
                .doOnComplete(latch::countDown)
//                .subscribeOn(Schedulers.parallel())
                .subscribe(new DefSubscriber<>()));

        latch.await();
        Util.sleepSeconds(1);

        System.out.println("==============");
        System.out.println(set.size());
        System.out.println(set);
    }
}
