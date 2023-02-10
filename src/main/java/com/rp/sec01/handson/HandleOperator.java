package com.rp.sec01.handson;

import reactor.core.publisher.Flux;

public class HandleOperator {
    public static void main(String[] args) {
        Flux.range(1, 20)
                .handle((i, sync) -> {
                    if (i % 2 == 0 ) {
                        sync.next(i);
                    } else {
                        sync.next(i * 10);
                    }
                })
                .take(3)
                .doOnComplete(()-> System.out.println("END"))
                .doFirst(()-> System.out.println("Start"))
                .doFinally((c)-> System.out.println("Finally:" + c))
                .doOnDiscard(Object.class, i -> System.out.println("Discarded: " + i))
                .subscribe(System.out::println);
    }
}
