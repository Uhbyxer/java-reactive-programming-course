package com.rp.sec01.handson;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoBlocked {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hi");
        String result = mono.subscribeOn(Schedulers.boundedElastic()).block(); //for testing only
        System.out.println(result);
    }
}
