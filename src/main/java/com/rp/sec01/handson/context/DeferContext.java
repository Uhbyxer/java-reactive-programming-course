package com.rp.sec01.handson.context;

import com.github.javafaker.Faker;
import com.rp.sec01.handson.DefSubscriber;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class DeferContext {
    public static void main(String[] args) {
        Mono<String> mono = Mono.deferContextual(
                contextView ->
                        Mono.just("Hello " + contextView.getOrDefault("user", "<ANONYMOUS>")));


        mono
                .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("user", Faker.instance().artist().name()))
                .subscribe(new DefSubscriber<>());
    }
}
