package org.tackedev.reactor.hot_cold_publisher;

import org.tackedev.reactor.Common;
import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotSharePublisherExample {

    public static void main(String[] args) {
        Flux<String> moviePublisher = Flux.fromStream(HotSharePublisherExample::getMovieStream)
                .delayElements(Duration.ofSeconds(2))
                .share();

        moviePublisher.subscribe(new DefaultSubscriber<>("Sam"));

        Common.sleepInSeconds(5);

        moviePublisher.subscribe(new DefaultSubscriber<>("John"));

        Common.sleepInSeconds(60);

    }

    private static Stream<String> getMovieStream() {
        System.out.println("Got the movie streaming request");
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 6",
                "Scene 7",
                "Scene 8",
                "Scene 9"
        );

    }
}
