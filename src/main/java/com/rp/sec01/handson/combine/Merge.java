package com.rp.sec01.handson.combine;

import com.github.javafaker.Faker;
import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Merge {
    public static void main(String[] args) {
        Flux<Object> animals = Flux.create(f -> {
            for (int i = 0; i < 10; i++) {
                f.next(Faker.instance().animal().name().toUpperCase());
            }
            f.complete();
        })//.publishOn(Schedulers.parallel())
                .subscribeOn(Schedulers.boundedElastic());

        Flux<Object> names = Flux.create(f -> {
            for (int i = 0; i < 10; i++) {
                f.next(Faker.instance().name().firstName());
            }
            f.complete();
        })//.publishOn(Schedulers.parallel())
                .subscribeOn(Schedulers.boundedElastic());

        Flux<Object> numbers = Flux.create(f -> {
            for (int i = 0; i < 10; i++) {
                f.next(String.valueOf(i));
                Util.sleepMillis(200);
            }
            f.complete();
        })//.publishOn(Schedulers.parallel())
        .subscribeOn(Schedulers.boundedElastic());

//        Flux<Object> flux = animals
//                .mergeWith(numbers)
//                .mergeWith(names);

        Flux<Object> flux = Flux.merge(animals, numbers, names);


        flux
                .subscribe(new DefSubscriber<>());

        Util.sleepSeconds(5);

    }
}
