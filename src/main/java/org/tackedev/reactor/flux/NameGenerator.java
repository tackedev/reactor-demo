package org.tackedev.reactor.flux;

import org.tackedev.reactor.Common;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class NameGenerator {

    public Flux<String> getNameFlux(int num) {
        return Flux.range(0, num).map(i -> getNameInLongTime());
    }

    public List<String> getNameList(int num) {
        return Stream.generate(this::getNameInLongTime).limit(num).toList();
    }

    private String getNameInLongTime() {
        Common.sleepInSeconds(1);
        return Common.getName();
    }

}
