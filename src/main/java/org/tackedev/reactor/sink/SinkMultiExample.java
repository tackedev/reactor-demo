package org.tackedev.reactor.sink;

import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkMultiExample {

    public static void main(String[] args) {

        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();
        flux.subscribe(new DefaultSubscriber<>("sam"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");

        flux.subscribe(new DefaultSubscriber<>("mike"));

        sink.tryEmitNext("?");
    }

}