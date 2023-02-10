package com.rp.sec01.handson.batch;

import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class BufSkipAndDrop {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(300))
//                .buffer(3, 1)
                .buffer(3, 10)
                .subscribe(new DefSubscriber<>());

        Util.sleepSeconds(20);

    }
}
