package com.rp.sec01.handson.sink;

import com.github.javafaker.Faker;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkMulticastOneToMany {
    public static void main(String[] args) {
        Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

        Flux<String> stringFlux = sink.asFlux();
        stringFlux.subscribe(new DefSubscriber<>());
        stringFlux.subscribe(new DefSubscriber<>());

        for (int i = 0; i < 5; i++) {
            sink.tryEmitNext(Faker.instance().ancient().titan());
        }

    }
}
