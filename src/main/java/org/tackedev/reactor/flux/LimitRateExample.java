package org.tackedev.reactor.flux;

import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

public class LimitRateExample {

    public static void main(String[] args) {
        Flux.range(0, 1000)
                .log()
                .limitRate(100)
                .subscribe(new DefaultSubscriber<>("LimitRateSubscriber"));
    }

}
