package org.tackedev.reactor.batch;

import org.tackedev.reactor.Common;
import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class WindownExample {

    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        eventFlux()
                .window(5)
                .flatMap(WindownExample::saveEvents)
                .subscribe(new DefaultSubscriber<>("WindowSubscriber"));

        Common.sleepInSeconds(60);

    }

    private static Flux<String> eventFlux() {
        return Flux.interval(Duration.ofMillis(500)).map(i -> "event" + i);
    }

    private static Mono<Integer> saveEvents(Flux<String> eventFlux) {
        return eventFlux.doOnNext(event -> System.out.println("Saving " + event))
                .doOnComplete(() ->
                        System.out.println("""
                                Saved this batch
                                -----------------------""")
                )
                .then(Mono.just(atomicInteger.getAndIncrement()));
    }

}
