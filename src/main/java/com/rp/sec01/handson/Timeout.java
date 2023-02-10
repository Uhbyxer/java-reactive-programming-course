package com.rp.sec01.handson;

import com.github.javafaker.Faker;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Timeout {
    public static void main(String[] args) {
        Flux.generate(f -> {
                    String name = Faker.instance().app().name();
                    if (name.toLowerCase().startsWith("y")) {
                        Util.sleepSeconds(1);
                    }
                    f.next(name);
                })//.delayElements(Duration.ofSeconds(1))
                .timeout(Duration.ofMillis(200))
                .subscribe(new DefSubscriber<>());

        Util.sleepSeconds(5);
    }
}
