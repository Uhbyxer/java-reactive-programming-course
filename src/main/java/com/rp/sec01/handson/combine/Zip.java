package com.rp.sec01.handson.combine;

import com.github.javafaker.Faker;
import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple3;

public class Zip {
    public static void main(String[] args) {
        Flux<Object> animals = Flux.create(f -> {
            for (int i = 0; i < 5; i++) {
                f.next(Faker.instance().animal().name().toUpperCase() + "_" + i);
            }
            f.complete();
        });//.publishOn(Schedulers.parallel())
               // .subscribeOn(Schedulers.boundedElastic());

        Flux<Object> names = Flux.create(f -> {
            for (int i = 0; i < 7; i++) {
                f.next(Faker.instance().name().firstName()+ "_" + i);
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
        .subscribeOn(Schedulers.parallel());

//        Flux<Object> flux = animals
//                .mergeWith(numbers)
//                .mergeWith(names);

        Flux<Tuple3<Object, Object, Object>> flux = Flux.zip(animals, numbers, names);


        flux
                .subscribe(new DefSubscriber<>());

        Util.sleepSeconds(5);

    }
}
