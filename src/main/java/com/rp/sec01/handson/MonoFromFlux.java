package com.rp.sec01.handson;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class MonoFromFlux {
    public static void main(String[] args) {
        Flux<Long> flux = Flux.interval(Duration.ofSeconds(1));
        Mono<Long> mono = Mono.from(flux);
        mono.subscribe(System.out::println);

        Util.sleepMillis(4000);
    }
}
