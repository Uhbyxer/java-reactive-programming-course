package com.rp.test.handson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

import java.time.Duration;
import java.util.List;

class FluxTest {
    @Test
    void test1() {

        StepVerifierOptions scenarioName = StepVerifierOptions.create().scenarioName("ttttt");

        Flux<Integer> flux = Flux.just(1, 2, 3);
        StepVerifier.create(flux, scenarioName)
                .expectSubscription()
                .expectNext(1, 2)
                .expectNext(3)
                .verifyComplete();
    }

    @Test
    void test2() {
        Flux<Integer> flux = Flux.range(1, 50);
        StepVerifier.create(flux)
                .expectSubscription()
                .expectNext(1)
                .as("next 1")
                .expectNextCount(49)
                .verifyComplete();
    }

    @Test
    void test3() {
        Flux<Integer> flux = Flux.range(1, 50);
        StepVerifier.create(flux)
                .expectSubscription()
                .thenConsumeWhile(i -> i < 51)
                .verifyComplete();
    }

    @Test
    void test4() {
        Flux<String> flux = Flux.fromIterable(List.of("AAA", "BBB")).delayElements(Duration.ofMillis(500));
        StepVerifier.create(flux)
                .expectSubscription()
                .assertNext(Assertions::assertNotNull)
                .assertNext(Assertions::assertNotNull)
                .verifyComplete();
    }


    @Test
    void testError() {
        Flux<Integer> flux = Flux.just(1, 2, 3);
        String errMsg = "ErrMsg";
        Flux<Object> error = Flux.error(new RuntimeException(errMsg));
        Flux<Object> concat = Flux.concat(flux, error);

        StepVerifier.create(concat)
                .expectSubscription()
                .expectNext(1, 2)
                .expectNext(3)
//                .verifyError(RuntimeException.class)
                .verifyErrorMatches(t -> t instanceof RuntimeException
                        && t.getMessage().equals(errMsg));
    }

    @Test
    void testVirtualTime() {
        StepVerifier.withVirtualTime(() -> Flux.range(1, 3)
                        .delayElements(Duration.ofSeconds(2)).map(x -> "a" + x))
                .thenAwait(Duration.ofSeconds(20))
                .expectNext("a1", "a2", "a3")
                .verifyComplete();
    }

    @Test
    void testVirtualTime2() {
        StepVerifier.withVirtualTime(() -> Flux.range(1, 3)
                        .delayElements(Duration.ofSeconds(5)).map(x -> "a" + x))
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(5))
                .thenAwait(Duration.ofSeconds(20))
                .expectNext("a1", "a2", "a3")
                .verifyComplete();
    }

}
