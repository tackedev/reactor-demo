package org.tackedev.reactor.combine_publisher;

import org.tackedev.reactor.Common;
import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class CombineLatestExample {

    public static void main(String[] args) {

        Flux.combineLatest(getStringFlux(), getIntFlux(), (s, i) -> s + i)
                .subscribe(new DefaultSubscriber<>("CombineLatestSubscriber"));

        Common.sleepInSeconds(10);
    }

    private static Flux<String> getStringFlux() {
        return Flux.just("A", "B", "C", "D")
                .delayElements(Duration.ofSeconds(1));
    }

    private static Flux<Integer> getIntFlux() {
        return Flux.just(1, 2, 3)
                .delayElements(Duration.ofSeconds(3));
    }

}
