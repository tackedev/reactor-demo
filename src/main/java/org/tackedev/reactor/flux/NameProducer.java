package org.tackedev.reactor.flux;

import org.tackedev.reactor.Common;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> fluxSink) {
        this.fluxSink = fluxSink;
    }

    public void produce() {
        this.fluxSink.next(Thread.currentThread().getName() + ": " + Common.getCountry());
    }
}
