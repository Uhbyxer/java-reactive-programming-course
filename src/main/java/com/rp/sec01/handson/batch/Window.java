package com.rp.sec01.handson.batch;

import com.github.javafaker.Faker;
import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Window {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(300))
//                .window(5)
                .window(Duration.ofMillis(700))
//                .flatMap(f -> f)
                .subscribe(f -> {f.subscribe(new DefSubscriber<>());});
//                .subscribe(new DefSubscriber<>());

        Util.sleepSeconds(200);
    }

}