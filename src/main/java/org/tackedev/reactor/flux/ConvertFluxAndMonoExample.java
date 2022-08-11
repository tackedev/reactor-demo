package org.tackedev.reactor.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ConvertFluxAndMonoExample {

    public static void main(String[] args) {
        Mono<String> strMono = Mono.just("a");
        Flux<String> strFlux = Flux.from(strMono);
        strFlux.subscribe(s -> System.out.println("Received: " + s));

        Flux<Integer> intFlux = Flux.range(0, 10);
        Mono<Integer> intMono = intFlux.next();
        intMono.subscribe(i -> System.out.println("Received: " + i));
    }

}
