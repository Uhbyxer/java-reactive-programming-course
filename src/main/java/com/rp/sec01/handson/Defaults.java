package com.rp.sec01.handson;

import reactor.core.publisher.Flux;

public class Defaults {
    public static void main(String[] args) {
        Flux.range(1, 20)
                .filter(i -> i > 100)
                .switchIfEmpty(Flux.range(-100, 5).filter(i -> i > 100))
                .defaultIfEmpty(-1)
                .subscribe(new DefSubscriber<>());
    }
}
