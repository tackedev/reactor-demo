package org.tackedev.reactor.backpressure;

import org.tackedev.reactor.Common;
import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class DropStrategyExample {

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");

        List<Object> list = new ArrayList<>();

        Flux.create(fluxSink -> {
                    for (int i = 0; i < 200; i++) {
                        fluxSink.next(i);
                        System.out.println("Published: " + i);
                        Common.sleepInMillis(1);
                    }
                    fluxSink.complete();
                })
                .onBackpressureDrop(list::add)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Common.sleepInMillis(10))
                .subscribe(new DefaultSubscriber<>("Backpressure Subscriber"));

        Common.sleepInSeconds(10);
        System.out.println(list);

    }

}
