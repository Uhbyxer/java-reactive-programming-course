package com.rp.sec01.handson;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class LimitRate {
    public static void main(String[] args) {
        Flux.range(1, 20)
                .log()
//                .limitRate(50)
//                .limitRate(1)
//                .limitRate(4, 3)
                .delayElements(Duration.ofSeconds(2))
                .subscribe(e ->  System.out.println(Util.getThread() + e));

        Util.sleepSeconds(30);
    }
}
