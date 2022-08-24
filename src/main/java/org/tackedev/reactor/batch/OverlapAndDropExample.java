package org.tackedev.reactor.batch;

import org.tackedev.reactor.Common;
import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class OverlapAndDropExample {

    public static void main(String[] args) {

        eventFlux()
                .buffer(3, 5)
                .subscribe(new DefaultSubscriber<>("OverlapAndDrop"));

        Common.sleepInSeconds(60);

    }

    private static Flux<String> eventFlux() {
        return Flux.interval(Duration.ofMillis(300)).map(i -> "event" + i);

    }

}
