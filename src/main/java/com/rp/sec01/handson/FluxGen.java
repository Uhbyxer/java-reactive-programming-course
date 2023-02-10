package com.rp.sec01.handson;

import reactor.core.publisher.Flux;

public class FluxGen {
    public static void main(String[] args) {
        Flux.generate(() -> 1, (counter, sink) -> {

            if (counter > 10) {
                sink.complete();
            } else {
                sink.next("No " + counter);
            }
            return counter + 1;
        }, (c)-> {
            System.out.println("Finished: " + c);
        }).subscribe(System.out::println);
    }
}
