package org.tackedev.reactor.combine_publisher;

import org.tackedev.reactor.Common;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    private List<String> list = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux.generate(synchronousSink -> {
                    System.out.println("Generated fresh");
                    Common.sleepInSeconds(1);
                    String name = Common.getName();
                    list.add(name);
                    synchronousSink.next(name);
                })
                .cast(String.class)
                .startWith(getFromCache());
    }

    private Flux<String> getFromCache() {
        return Flux.fromIterable(list);

    }

}
