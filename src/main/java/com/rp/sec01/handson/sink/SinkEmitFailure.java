package com.rp.sec01.handson.sink;

import com.github.javafaker.Faker;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Sinks;

public class SinkEmitFailure {
    public static void main(String[] args) {
        Sinks.One<String> sink = Sinks.one();

        sink.tryEmitValue(Faker.instance().animal().name());
        sink.emitValue(Faker.instance().animal().name(), (signalType, emitResult) -> {
            System.out.println("signalType = " + signalType.name());
            System.out.println("emitResult = " + emitResult.name());

            return false;
        });

        sink.asMono().subscribe(new DefSubscriber<>());

    }
}
