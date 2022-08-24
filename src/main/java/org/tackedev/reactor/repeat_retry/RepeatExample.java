package org.tackedev.reactor.repeat_retry;

import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class RepeatExample {

    private static final AtomicInteger counter = new AtomicInteger(1);

    public static void main(String[] args) {

        getIntegers()
                .repeat(() -> counter.get() < 14)
                .subscribe(new DefaultSubscriber<>("RepeatSubscriber"));

    }

    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
                .doOnSubscribe(subscription -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("Completed"))
                .map(i -> counter.getAndIncrement());
    }

}
