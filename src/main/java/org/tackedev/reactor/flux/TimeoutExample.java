package org.tackedev.reactor.flux;

import org.tackedev.reactor.Common;
import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class TimeoutExample {

    public static void main(String[] args) {

        Flux<Integer> fallback = Flux.range(100, 10)
                        .delayElements(Duration.ofMillis(200));

        Flux.range(0, 10)
                .log()
                .delayElements(Duration.ofSeconds(5))
                .timeout(Duration.ofSeconds(2), fallback)
                .subscribe(new DefaultSubscriber<>("TimeoutSubscriber"));

        Common.sleepInSeconds(60);

    }

}
