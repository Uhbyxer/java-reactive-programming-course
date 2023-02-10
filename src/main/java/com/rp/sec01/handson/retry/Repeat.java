package com.rp.sec01.handson.retry;

import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Repeat {
    public static void main(String[] args) {
        Flux.range(1, 3)
                .delayElements(Duration.ofMillis(500))
                .repeat(2)
                .subscribe(new DefSubscriber<>());

        Util.sleepSeconds(20);
    }
}
