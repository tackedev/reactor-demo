package org.tackedev.reactor.sink;

import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SInkReplyExample {

    public static void main(String[] args) {

        Sinks.Many<Object> sink = Sinks.many().replay().all();

        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");

        flux.subscribe(new DefaultSubscriber<>("sam"));
        flux.subscribe(new DefaultSubscriber<>("mike"));

        sink.tryEmitNext("?");

        flux.subscribe(new DefaultSubscriber<>("jake"));

        sink.tryEmitNext("new msg");
    }

}