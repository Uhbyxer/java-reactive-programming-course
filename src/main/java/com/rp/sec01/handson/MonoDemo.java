package com.rp.sec01.handson;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


public class MonoDemo {
    public static void main(String[] args) {
//        Mono<Integer> mono = Mono.just(1).map(x->x+1);
//        Mono<Integer> mono = Mono.empty();
//        Mono<Integer> mono = Mono.error(new RuntimeException("Hohoho"));
//        Mono<Integer> mono = Mono.fromSupplier(() -> 7);
        Mono<Integer> mono = Mono.fromCallable(() -> 7);
        System.out.println(Thread.currentThread().getName());
//        mono.subscribe(System.out::println);
//        mono.subscribe((m)->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(Thread.currentThread().getName() + " " + m);
//        });
        mono
//                .subscribeOn(Schedulers.boundedElastic())
                .subscribe((m) -> {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println(Thread.currentThread().getName() + " " + m);
                        },
                        System.err::println, () -> System.out.println(Thread.currentThread().getName() + " Finished !"));


//    Util.sleepSeconds(8);

    }


}
