package com.rp.sec01.handson;

import reactor.core.publisher.Flux;

public class TakeDemo {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(0, 20);
        flux.takeLast(3).subscribe(System.out::println);


        Flux.generate(s -> {
            s.next("AAAA");
//            s.next("AAAA");
//            s.complete();
        }).take(5).subscribe(System.out::println);
    }
}
