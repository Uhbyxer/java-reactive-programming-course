package com.rp.sec01.handson;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFromFutureAndRunnable {
    public static void main(String[] args) {
//        Mono<String> mono = Mono.fromFuture(getFuture());

        Mono<Object> mono = Mono.fromRunnable(() -> {
            System.out.println("Runnable: " + Thread.currentThread().getName());
            Util.sleepSeconds(2);
        });

        mono.subscribe(System.out::println, System.err::println, () -> {
            System.out.println("Completed !");
        });


        Util.sleepSeconds(4);



    }

    private static CompletableFuture<String> getFuture() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Future: " + Thread.currentThread().getName());
            Util.sleepSeconds(2);
            return "XXX";
        });
    }
}
