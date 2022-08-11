package org.tackedev.reactor.flux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.tackedev.reactor.Common;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class SubscriptionExample {

    public static void main(String[] args) {
        AtomicReference<Subscription> atomicSubscription = new AtomicReference<>();

        Flux.range(0, 20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("Received subscription: " + subscription);
                        atomicSubscription.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError: " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onCompelete");
                    }
                });

        Common.sleepInSeconds(3);
        atomicSubscription.get().request(3);
        Common.sleepInSeconds(5);
        atomicSubscription.get().request(3);
        Common.sleepInSeconds(5);
        System.out.println("Going to cancel");
        atomicSubscription.get().cancel();
        Common.sleepInSeconds(3);
        atomicSubscription.get().request(4);
        Common.sleepInSeconds(5);
    }

}
