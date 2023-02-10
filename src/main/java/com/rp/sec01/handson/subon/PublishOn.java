package com.rp.sec01.handson.subon;

import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PublishOn {
    public static void main(String[] args) {
        Flux<String> flux = Flux.create(f -> {
                    for (int i = 0; i < 10; i++) {
                        f.next("" + i);
                        Util.sleepMillis(300);
                    }
                    f.complete();
                })
                .publishOn(Schedulers.parallel())
                .map(x-> "Elem: " + x.toString())
                .map(String::toUpperCase)
                .doOnSubscribe(c -> System.out.println(Util.getThread() + "doOnSubscribe"))
                .doOnRequest(c -> System.out.println(Util.getThread() + "doOnRequest"))
                .doOnComplete(() -> System.out.println(Util.getThread() + "doOnComplete"))
                .doOnNext(c -> System.out.println(Util.getThread() + "doOnNext"))
                .subscribeOn(Schedulers.boundedElastic())
                ;

        flux.subscribe(new DefSubscriber<>());
        Util.sleepSeconds(10);

    }
}
