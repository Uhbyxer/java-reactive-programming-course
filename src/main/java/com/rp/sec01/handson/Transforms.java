package com.rp.sec01.handson;

import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Transforms {

    public static void main(String[] args) {
        Flux<String> flux = Flux.range(1, 9)
                .transform(trans());
        flux.subscribe(new DefSubscriber<>());

    }

    private static Function<Flux<Integer>, Flux<String>> trans() {
        return f -> f.filter(x -> x > 5).map(x -> "Num " + x);
    }
}
