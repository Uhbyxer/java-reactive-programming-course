package com.rp.sec01.handson;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotSubAutoConnect {
    public static void main(String[] args) {

        Flux<String> flux = Flux.fromStream(Stream.of("A1", "A2", "A3", "A4", "A5", "A6"))
                .delayElements(Duration.ofSeconds(1))
                .publish().autoConnect(0);
        Util.sleepSeconds(2);
        System.out.println("=========== Start 1");
        flux.subscribe(new DefSubscriber<>());
        Util.sleepSeconds(2);
        System.out.println("=========== Start 2");

        flux.subscribe(new DefSubscriber<>());

        Util.sleepSeconds(15);
    }
}
