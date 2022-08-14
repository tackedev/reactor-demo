package org.tackedev.reactor.flux;

import org.tackedev.reactor.subscriber.DefaultSubscriber;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class SwitchOnFirstExample {

    public static void main(String[] args) {
        getPerson()
                .switchOnFirst((signal, personFlux) -> signal.isOnNext() ? applyFilterMap().apply(personFlux) : personFlux)
                .subscribe(new DefaultSubscriber<>("PersonSubscriber"));
    }

    public static Flux<Person> getPerson() {
        return Flux.range(0, 10).map(i -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
        return flux -> flux
                .filter(person -> person.getAge() > 18)
                .doOnNext(person -> person.setName(person.getName().toUpperCase()))
                .doOnDiscard(Person.class, person -> System.out.println("Not allowing:" + person));
    }

}
