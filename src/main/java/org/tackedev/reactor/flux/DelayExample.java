package org.tackedev.reactor.flux;

import org.tackedev.reactor.Common;
import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class DelayExample {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.x", "9");

        Flux.range(0, 100)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(new DefaultSubscriber<>("DelaySubscriber"));

        Common.sleepInSeconds(60);
    }

}
