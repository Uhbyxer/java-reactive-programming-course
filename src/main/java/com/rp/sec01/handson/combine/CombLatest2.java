package com.rp.sec01.handson.combine;

import com.github.javafaker.Faker;
import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;

public class CombLatest2 {
    public static void main(String[] args) {
        Flux<Object> animals = Flux.just("a", "b");//.publishOn(Schedulers.parallel())
               // .subscribeOn(Schedulers.boundedElastic());

        Flux<Object> numbers = Flux.just("1", "2");

        Flux<Object> flux = Flux.combineLatest(animals, numbers, (a, b) -> a.toString() + ":" + b.toString());


        flux
                .subscribe(new DefSubscriber<>());

        Util.sleepSeconds(5);

    }
}
