package org.tackedev.reactor.backpressure;

import org.tackedev.reactor.Common;
import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Example {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
                    for (int i = 0; i < 500; i++) {
                        fluxSink.next(i);
                        System.out.println("Published: " + i);
                    }
                    fluxSink.complete();
                })
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Common.sleepInMillis(10))
                .subscribe(new DefaultSubscriber<>("Backpressure Subscriber"));

        Common.sleepInSeconds(60);

    }

}
