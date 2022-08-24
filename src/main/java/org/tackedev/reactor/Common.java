package org.tackedev.reactor;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Common {
    private static final Faker FAKER = Faker.instance();

    public static String getCreditCardNumber() {
        return FAKER.business().creditCardNumber();
    }

    public static String getIdNumber() {
        return FAKER.idNumber().valid();
    }

    public static int getRandomInt(int min, int max) {
        return FAKER.number().numberBetween(min, max);
    }

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

    public static void sleepInMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
