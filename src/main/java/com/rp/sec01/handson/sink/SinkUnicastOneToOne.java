package com.rp.sec01.handson.sink;

import com.github.javafaker.Faker;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Sinks;

public class SinkUnicastOneToOne {
    public static void main(String[] args) {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();
        for (int i = 0; i < 5; i++) {
            sink.tryEmitNext(Faker.instance().ancient().titan());
        }

        sink.asFlux().subscribe(new DefSubscriber<>());
        sink.asFlux().subscribe(new DefSubscriber<>());
    }
}
