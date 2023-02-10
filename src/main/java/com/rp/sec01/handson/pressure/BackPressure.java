package com.rp.sec01.handson.pressure;

import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.concurrent.Queues;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BackPressure {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "495");

        List<Object> backout = new CopyOnWriteArrayList<>();

        Flux.create(f -> {
                    for (int i = 0; i < 500; i++) {
                        f.next(Util.getThread() + "Element " + i);
                        Util.sleepMillis(2);
                    }
                    f.complete();
                })
//                .onBackpressureError()
                .onBackpressureLatest()
//                .onBackpressureBuffer(20)
//                .onBackpressureDrop(backout::add)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(o -> {
                    System.out.println(Util.getThread() + "onNext");
                    Util.sleepMillis(20);
                })
                .doOnComplete(() -> System.out.println(Util.getThread() + backout))
                .subscribeOn(Schedulers.parallel())
                .subscribe(new DefSubscriber<>(0));


        Util.sleepSeconds(300);

    }
}
