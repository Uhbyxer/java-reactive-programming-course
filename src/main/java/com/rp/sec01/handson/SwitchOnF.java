package com.rp.sec01.handson;

import reactor.core.publisher.Flux;

import java.util.function.Function;

public class SwitchOnF {

    public static void main(String[] args) {
        var flux = Flux.range(-1, 9)
                .switchOnFirst(((signal, integerFlux) -> {
                    if (signal.isOnNext() && signal.get() > 0 )
                    return integerFlux;
                    else return trans().apply(integerFlux);
                }));
        flux.subscribe(new DefSubscriber<>());

    }

    private static Function<Flux<Integer>, Flux<String>> trans() {
        return f -> f.filter(x -> x > 5).map(x -> "Num " + x);
    }
}
