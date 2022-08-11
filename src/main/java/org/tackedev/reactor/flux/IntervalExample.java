package org.tackedev.reactor.flux;

import org.tackedev.reactor.Common;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class IntervalExample {

    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1))
                .subscribe(l -> System.out.println("Received: " + l));

        Common.sleepInSeconds(5);

    }

}
