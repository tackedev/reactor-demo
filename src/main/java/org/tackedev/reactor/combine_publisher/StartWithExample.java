package org.tackedev.reactor.combine_publisher;

import org.tackedev.reactor.subscriber.DefaultSubscriber;

public class StartWithExample {

    public static void main(String[] args) {

        NameGenerator generator = new NameGenerator();
        generator.generateNames()
                .take(2)
                .subscribe(new DefaultSubscriber<>("NameSubscriber-1"));

        generator.generateNames()
                .take(2)
                .subscribe(new DefaultSubscriber<>("NameSubscriber-2"));

    }

}
