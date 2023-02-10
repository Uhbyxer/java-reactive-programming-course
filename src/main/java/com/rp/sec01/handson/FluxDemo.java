package com.rp.sec01.handson;

import com.github.javafaker.Faker;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FluxDemo {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("a", "b", "c", "d");
        flux.subscribe(Util::print);

        Stream<Integer> stream = IntStream.range(0, 5).boxed();
        Flux.fromStream(stream).subscribe(Util::print);
//        Flux.fromStream(stream).subscribe(Util::print);

        var flux1 = Flux.range(0, 5).log().map(i -> Faker.instance().ancient().hero()).log();
        flux1.subscribe(Util::print);
    }
}
