package com.rp.sec01.handson;

import reactor.core.publisher.Flux;

public class Fallback {
    public static void main(String[] args) {
        Flux.range(1, 20)
                .map(x-> 2*x / (5-x))
                .onErrorReturn(-100)
//                .onErrorContinue((t, e) -> {
//
//                })
                .subscribe(System.out::println);
    }
}
