package org.tackedev.reactor.mono;

import org.tackedev.reactor.Common;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class MonoCreationExample {

    public static void main(String[] args) {

        Mono.just(1).subscribe(i -> System.out.println("Received: " + i));

        Mono.fromSupplier(Common::getName)
                .subscribe(s -> System.out.println("Received: " + s));

        Mono.fromSupplier(MonoCreationExample::getNameInLongTime)
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(s -> System.out.println("Received: " + s));

        String name = Mono.fromSupplier(MonoCreationExample::getNameInLongTime)
                .subscribeOn(Schedulers.boundedElastic())
                .block();
        System.out.println("Received: " + name);

        Mono.fromFuture(getNameFuture())
                .subscribe(s -> System.out.println("Received: " + s));
        Common.sleepInSeconds(1);

        Mono.fromCallable(getNameCallable())
                .subscribe(
                        s -> System.out.println("Received: " + s),
                        Throwable::printStackTrace,
                        () -> System.out.println("Runnable completed!")
                );
    }

    public static Callable<String> getNameCallable() {
        return MonoCreationExample::getNameInLongTime;
    }

    public static CompletableFuture<String> getNameFuture() {
        return CompletableFuture.supplyAsync(Common::getName);
    }

    public static String getNameInLongTime() {
        System.out.println("Generating name...");
        Common.sleepInSeconds(3);
        return Common.getName();
    }


}
