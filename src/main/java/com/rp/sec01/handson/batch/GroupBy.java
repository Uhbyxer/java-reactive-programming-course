package com.rp.sec01.handson.batch;

import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class GroupBy {
    public static void main(String[] args) {
        Flux.range(1, 20)
                .delayElements(Duration.ofMillis(700))
                .groupBy(i -> i % 2 == 0)
                .subscribe(c -> c.subscribe(x -> System.out.println(c.key() + " " + x)));


        Util.sleepSeconds(20);
    }
}
