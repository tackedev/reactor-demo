package org.tackedev.reactor;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Common {
    private static final Faker FAKER = Faker.instance();

    public static String getName() {
        return FAKER.name().fullName();
    }

    public static String getCountry() {
        return FAKER.country().name();
    }

    public static void sleepInSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
