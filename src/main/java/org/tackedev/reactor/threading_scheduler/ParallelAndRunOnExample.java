package org.tackedev.reactor.threading_scheduler;

import org.tackedev.reactor.Common;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelAndRunOnExample {

    public static void main(String[] args) {

        Flux.range(0, 10)
                .parallel()
                .runOn(Schedulers.parallel())
                .doOnNext(o -> printThreadName("Next " + o))
                .subscribe(o -> printThreadName("Subscribe " + o));

        Common.sleepInSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println("|" + Thread.currentThread().getName() + "|" + msg);
    }

}