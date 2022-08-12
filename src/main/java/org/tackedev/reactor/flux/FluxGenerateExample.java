package org.tackedev.reactor.flux;

import org.tackedev.reactor.Common;
import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class FluxGenerateExample {

    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger();
        Flux.generate(synchronousSink -> {
                    synchronousSink.next(Common.getCountry());
                    counter.getAndIncrement();
                    if (counter.get() == 10) {
                        synchronousSink.complete();
                    }
                })
                .subscribe(new DefaultSubscriber<>("FluxGenerate"));

        Flux.generate(() -> 1, (state, sink) -> {
                    sink.next(Common.getCountry());
                    if (state == 10) {
                        sink.complete();
                    }
                    return state + 1;
                })
                .subscribe(new DefaultSubscriber<>("FluxGenerateWithState"));
    }

}
