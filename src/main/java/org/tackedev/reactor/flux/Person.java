package org.tackedev.reactor.flux;

import lombok.Data;
import org.tackedev.reactor.Common;

@Data
public class Person {
    private String name;
    private int age;

    public Person() {
        this.name = Common.getName();
        this.age = Common.getRandomInt(1, 65);
    }
}
