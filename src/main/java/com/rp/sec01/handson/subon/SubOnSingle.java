package com.rp.sec01.handson.subon;

import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubOnSingle {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(f -> {
                    for (int i = 0; i < 10; i++) {
                        f.next("Element " + i);
                        Util.sleepMillis(300);
                    }
                    f.complete();
                })
                .subscribeOn(Schedulers.single());

        flux.subscribe(new DefSubscriber<>());
        flux.subscribe(new DefSubscriber<>());

        Util.sleepSeconds(10);
    }
}
