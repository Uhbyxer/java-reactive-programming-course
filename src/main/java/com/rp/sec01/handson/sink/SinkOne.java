package com.rp.sec01.handson.sink;

import com.github.javafaker.Faker;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Sinks;

public class SinkOne {
    public static void main(String[] args) {
        Sinks.One<String> sink = Sinks.one();

        sink.tryEmitValue(Faker.instance().animal().name());

        sink.asMono().subscribe(new DefSubscriber<>());

    }
}
