package org.tackedev.reactor.flux;

import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

public class DoHookExample {

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
                    System.out.println("Inside create");
                    for (int i = 0; i < 5; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                    System.out.println("Completed");
                })
                .doOnComplete(() -> System.out.println("Inside doOnComplete"))
                .doFirst(() -> System.out.println("Inside doFirst"))
                .doOnNext(o -> System.out.println("Inside doOnNext: " + o))
                .doOnSubscribe(subscription -> System.out.println("Inside doOnSubscribe: " + subscription))
                .doOnRequest(value -> System.out.println("Inside doOnRequest: " + value))
                .doOnError(throwable -> System.out.println("Inside doOnError: " + throwable))
                .doOnTerminate(() -> System.out.println("Inside doOnTerminate"))
                .doOnCancel(() -> System.out.println("Inside doOnCancel"))
                .doFinally(signalType -> System.out.println("Inside doFinally: " + signalType))
                .doOnDiscard(Object.class, o -> System.out.println("Inside doOnDiscard: " + o))
                .subscribe(new DefaultSubscriber<>("FluxWithDoHook"));
    }

}
