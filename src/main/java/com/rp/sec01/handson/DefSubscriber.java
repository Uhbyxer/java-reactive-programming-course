package com.rp.sec01.handson;

import com.rp.courseutil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;

public class DefSubscriber<T> implements Subscriber<T> {

    private static final AtomicLong counter = new AtomicLong(0);

    private final String name;
    private int waitMillis = 0;

    public DefSubscriber(String name) {
        this.name = name;
    }

    public DefSubscriber() {
        this("Subscriber-" + counter.getAndIncrement());
    }

    public DefSubscriber(int waitMillis) {
        this("Subscriber-" + counter.getAndIncrement());
        this.waitMillis = waitMillis;
    }


    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T t) {
        System.out.println(getInfo() + "Received: " + t);
        if (waitMillis > 0) {
            Util.sleepMillis(waitMillis);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println(getInfo() + "Error: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(getInfo() + "Completed !");
    }

    private String getInfo() {
        return "[" + name + "] " + Util.getThread();
    }
}
