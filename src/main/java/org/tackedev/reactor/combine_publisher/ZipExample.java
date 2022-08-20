package org.tackedev.reactor.combine_publisher;

import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

public class ZipExample {

    public static void main(String[] args) {

        Flux.zip(getBody(), getEngine(), getTires())
                .subscribe(new DefaultSubscriber<>("ZipSubscriber"));
    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "body " + i);
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 2)
                .map(i -> "engine " + i);
    }

    private static Flux<String> getTires() {
        return Flux.range(1, 6)
                .map(i -> "tires " + i);
    }
}
