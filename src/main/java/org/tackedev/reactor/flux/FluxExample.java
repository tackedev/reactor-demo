package org.tackedev.reactor.flux;

import org.tackedev.reactor.Common;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class FluxExample {

    public static void main(String[] args) {

        Flux.just(1, 2, 3, "hello", Common.getName(), Common.getName()).subscribe(
                i -> System.out.println("Received: " + i),
                Throwable::printStackTrace,
                () -> System.out.println("Completed!")
        );

        List<String> stringData = Arrays.asList("a", "b", "c");
        Flux.fromIterable(stringData)
                .subscribe(str -> System.out.println("Received: " + str));

        Integer[] intData = {2, 5, 7, 8, 10};
        Flux.fromArray(intData)
                .subscribe(i -> System.out.println("Received: " + i));


        Flux<Integer> intStreamFlux = Flux.fromStream(() -> Arrays.stream(intData));

        intStreamFlux.subscribe(
                i -> System.out.println("Received: " + i),
                Throwable::printStackTrace,
                () -> System.out.println("Completed!")
        );

        intStreamFlux.subscribe(
                i -> System.out.println("Received: " + i),
                Throwable::printStackTrace,
                () -> System.out.println("Completed!")
        );

        Flux.range(0, 3)
                .log()
                .map(i -> Common.getName())
                .log()
                .subscribe(name -> System.out.println("Received: " + name));
    }

}
