package org.tackedev.reactor.repeat_retry;

import org.tackedev.reactor.Common;
import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

public class RetryExample {

    public static void main(String[] args) {

        getIntegers()
                .retry(2)
                .subscribe(new DefaultSubscriber<>("RepeatSubscriber"));

    }

    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
                .doOnSubscribe(subscription -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("Completed"))
                .map(i -> i / Common.getRandomInt(0, 2))
                .doOnError(throwable -> System.out.println("Error: " + throwable));
    }

}
