package org.tackedev.reactor.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DefaultSubscriber<T> implements Subscriber<T> {
    private final String name;

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T event) {
        System.out.println("|" + name + "|Received: " + event);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("|" + name + "|Error: " + t);
    }

    @Override
    public void onComplete() {
        System.out.println("|" + name + "|Completed");
    }

}
