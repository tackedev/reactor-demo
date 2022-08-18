package org.tackedev.reactor.threading_scheduler;

import org.tackedev.reactor.Common;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOnMultipleExample {

    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
                    printThreadName("Created!");
                    for (int i = 0; i < 20; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .doOnNext(o -> printThreadName("Next " + o));

        flux
                .subscribeOn(Schedulers.parallel())
                .subscribe(o -> printThreadName("Subscribe " + o));

        Common.sleepInSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println("|" + Thread.currentThread().getName() + "|" + msg);
    }

}
