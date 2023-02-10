package com.rp.sec01.handson.sink;

import com.github.javafaker.Faker;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkMulticastDisableHistoryReplay {
    public static void main(String[] args) {
        Sinks.Many<String> sink = Sinks.many().replay().all();


        for (int i = 0; i < 2; i++) {
            sink.tryEmitNext(i + " " + Faker.instance().ancient().titan());
        }

        Flux<String> stringFlux = sink.asFlux();
        stringFlux.subscribe(new DefSubscriber<>());
        stringFlux.subscribe(new DefSubscriber<>());


        for (int i = 2; i < 4; i++) {
            sink.tryEmitNext(i + " " + Faker.instance().ancient().titan());
        }

    }
}
