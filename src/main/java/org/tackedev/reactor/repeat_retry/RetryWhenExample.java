package org.tackedev.reactor.repeat_retry;

import org.tackedev.reactor.Common;
import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

public class RetryWhenExample {

    public static void main(String[] args) {

        orderService(Common.getCreditCardNumber())
                .doOnError(throwable -> System.out.println("Error: " + throwable))
                .retryWhen(Retry.from(
                        flux -> flux.doOnNext(retrySignal -> {
                                    System.out.println(retrySignal.totalRetries());
                                    System.out.println(retrySignal.failure());
                                })
                                .handle((retrySignal, synchronousSink) -> {
                                    if ("500".equals(retrySignal.failure().getMessage())) {
                                        synchronousSink.next(1);
                                    } else {
                                        synchronousSink.error(retrySignal.failure());
                                    }
                                })
                                .delayElements(Duration.ofSeconds(1))
                ))
                .subscribe(new DefaultSubscriber<>("OrderSubscriber"));

        Common.sleepInSeconds(60);

    }

    private static Mono<String> orderService(String ccNumber) {
        return Mono.fromSupplier(() -> {
            processPayment(ccNumber);
            return Common.getIdNumber();
        });
    }

    private static void processPayment(String ccNumber) {
        int random = Common.getRandomInt(0, 10);
        if (random < 8) {
            throw new RuntimeException("500");
        } else if (random < 10) {
            throw new RuntimeException("404");
        }
    }

}
