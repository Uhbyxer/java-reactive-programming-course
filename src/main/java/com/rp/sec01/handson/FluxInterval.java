package com.rp.sec01.handson;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class FluxInterval {
    public static void main(String[] args) {
        Flux<Long> flux = Flux.interval(Duration.ofSeconds(1));


        for (int i = 0; i < 100; i++) {
            flux.subscribe(f -> {
                System.out.println(Util.getThread() + f);
                Util.sleepMillis(500);
            });
        }

        Util.sleepSeconds(5);
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
