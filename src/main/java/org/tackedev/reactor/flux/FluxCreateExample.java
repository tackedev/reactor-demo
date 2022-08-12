package org.tackedev.reactor.flux;

import org.tackedev.reactor.Common;
import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

public class FluxCreateExample {

    public static void main(String[] args) {

        NameProducer nameProducer = new NameProducer();

        Flux.create(nameProducer).subscribe(new DefaultSubscriber<>("NameProducerFlux"));

        Runnable runnable = nameProducer::produce;

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

        Common.sleepInSeconds(2);
    }

}
