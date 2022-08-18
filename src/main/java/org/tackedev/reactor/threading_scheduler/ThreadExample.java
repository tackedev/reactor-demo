package org.tackedev.reactor.threading_scheduler;

import org.tackedev.reactor.Common;
import reactor.core.publisher.Flux;

public class ThreadExample {

    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
                    printThreadName("Created!");
                    fluxSink.next(1);
                })
                .doOnNext(o -> printThreadName("Next " + o));

        Runnable runnable = () -> flux.subscribe(o -> printThreadName("Subscribe " + o));
        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }

        Common.sleepInSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println("|" + Thread.currentThread().getName() + "|" + msg);
    }

}
