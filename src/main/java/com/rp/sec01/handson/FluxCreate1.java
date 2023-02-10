package com.rp.sec01.handson;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;

public class FluxCreate1 {
    public static void main(String[] args) {
        Flux.create(e -> {
//        Flux.push(e -> {
            String c;
            do {
                c = Faker.instance().country().name();
                e.next(c);
            } while (!c.contains("United"));
            e.complete();
        }).subscribe(new DefSubscriber<>());
    }
}
