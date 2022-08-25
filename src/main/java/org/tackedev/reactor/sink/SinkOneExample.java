package org.tackedev.reactor.sink;

import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkOneExample {

    public static void main(String[] args) {

        Sinks.One<Object> sink = Sinks.one();

        Mono<Object> mono = sink.asMono();
        mono.subscribe(new DefaultSubscriber<>("SinkSubscriber"));

        sink.tryEmitValue("hi");
    }

}
