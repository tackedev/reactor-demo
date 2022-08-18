package org.tackedev.reactor.threading_scheduler;

import org.tackedev.reactor.Common;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOnExample {

    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
                    printThreadName("Created!");
                    fluxSink.next(1);
                })
                .subscribeOn(Schedulers.newParallel("Parallel SubscribeOn"))
                .doOnNext(o -> printThreadName("Next " + o));

        flux
                .doFirst(() -> printThreadName("doFirst 2"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("doFirst 1"))
                .subscribe(o -> printThreadName("Subscribe " + o));

        Common.sleepInSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println("|" + Thread.currentThread().getName() + "|" + msg);
    }

}
