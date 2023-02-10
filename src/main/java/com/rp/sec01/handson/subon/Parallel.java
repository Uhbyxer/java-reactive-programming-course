package com.rp.sec01.handson.subon;

import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Parallel {
    public static void main(String[] args) {
        var flux = Flux.create(f -> {
                    for (int i = 0; i < 10; i++) {
                        f.next("" + i);
                        Util.sleepMillis(300);
                    }
                    f.complete();
                })
                .parallel().runOn(Schedulers.boundedElastic())
                ;

        flux.subscribe(new DefSubscriber<>());
        Util.sleepSeconds(10);

    }
}
