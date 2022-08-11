package org.tackedev.reactor.flux;

import java.util.List;

public class FluxVsListExample {

    private static final NameGenerator nameGenerator = new NameGenerator();

    public static void main(String[] args) {
        List<String> names = nameGenerator.getNameList(5);
        System.out.println(names);

        nameGenerator.getNameFlux(5)
                .subscribe(s -> System.out.println("Received: " + s));
    }

}
