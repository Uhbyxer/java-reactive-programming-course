package com.rp.sec01.handson;

import com.rp.courseutil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

import static com.rp.courseutil.Util.getThread;

public class SubscriptionDemo {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(0, 20);
        AtomicReference<Subscription> sub = new AtomicReference<>();

        flux.subscribeWith(new Subscriber<Integer>() {

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println(getThread() + "On sub");
                sub.set(subscription);
            }

            @Override
            public void onNext(Integer integer) {
                Util.sleepSeconds(1);
                System.out.println(getThread() + "next: " + integer);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.println(getThread() + "Finish");
            }
        });

        new Thread(() -> {
          Util.sleepSeconds(5);
          sub.get().cancel();
        }).start();

//        sub.get().request(3);
//        sub.get().request(3);
       // sub.get().cancel();
        sub.get().request(50);
        sub.get().request(50);
    }
}
