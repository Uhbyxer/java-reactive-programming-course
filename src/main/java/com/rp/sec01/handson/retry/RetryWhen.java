package com.rp.sec01.handson.retry;

import com.rp.courseutil.Util;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

public class RetryWhen {
    public static void main(String[] args) {
        Flux.range(1, 30)
                .delayElements(Duration.ofMillis(500))
                .map(x -> {
                    if (x == 5) {
                        return x / 0;
                    }
                    return x;
                })
                .doOnSubscribe(c -> System.out.println("========= on Sub"))
                .doOnError(c -> System.err.println(c.getMessage()))
                .retryWhen(Retry.backoff(5, Duration.ofSeconds(1)))
                .subscribe(new DefSubscriber<>());

        Util.sleepSeconds(20);

    }
}
