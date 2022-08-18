package org.tackedev.reactor.backpressure;

import org.tackedev.reactor.Common;
import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ErrorStrategyExample {

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(fluxSink -> {
                    for (int i = 0; i < 200 && !fluxSink.isCancelled(); i++) {
                        fluxSink.next(i);
                        System.out.println("Published: " + i);
                        Common.sleepInMillis(1);
                    }
                    fluxSink.complete();
                })
                .onBackpressureError()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Common.sleepInMillis(10))
                .subscribe(new DefaultSubscriber<>("Backpressure Subscriber"));

        Common.sleepInSeconds(10);

    }

}