package com.rp.sec01.handson.combine;

import com.github.javafaker.Faker;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;

public class StartWith {
    public static void main(String[] args) {

        Flux<Object> animals = Flux.create(f -> {
            for (int i = 0; i < 3; i++) {
                f.next(Faker.instance().animal().name());
            }
            f.complete();
        });

        Flux.generate(s -> {
            s.next(Faker.instance().ancient().god());

        })
                .startWith(animals.concatWith(Flux.just("Cronus", "Mars")))
                .take(10)
                .subscribe(new DefSubscriber<>());
    }
}
