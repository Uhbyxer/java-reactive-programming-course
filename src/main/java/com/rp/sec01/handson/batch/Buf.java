package com.rp.sec01.handson.batch;

import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Buf {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(300))
//                .buffer()
//                .buffer(3)
//                .buffer(Duration.ofMillis(1400))
                .bufferTimeout(4, Duration.ofMillis(800))
                .subscribe(new DefSubscriber<>());

        Util.sleepSeconds(20);
    }
}
