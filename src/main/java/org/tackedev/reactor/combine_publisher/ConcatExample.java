package org.tackedev.reactor.combine_publisher;

import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

public class ConcatExample {

    public static void main(String[] args) {

        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.just("c", "d", "e");

        Flux<String> flux = flux1.concatWith(flux2);
        flux.subscribe(new DefaultSubscriber<>("ConcatFluxSubscriber"));

    }

}
