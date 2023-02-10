package com.rp.sec01.handson.sink;

import com.github.javafaker.Faker;
import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class SinkMulticastDirectBestEffort {
    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

//        Sinks.Many<String> sink = Sinks.many().multicast().directAllOrNothing();
        Sinks.Many<String> sink = Sinks.many().multicast().directBestEffort();


        Flux<String> stringFlux = sink.asFlux();
        stringFlux.subscribe(new DefSubscriber<>());
        stringFlux.delayElements(Duration.ofMillis(200)).subscribe(new DefSubscriber<>());


        for (int i = 0; i < 100; i++) {
            sink.tryEmitNext(i + " " + Faker.instance().ancient().titan());
        }


        Util.sleepSeconds(10);
    }
}
