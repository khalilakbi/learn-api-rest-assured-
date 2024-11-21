package data;

import com.github.javafaker.Faker;

public class RandomData {

    static Faker faker = new Faker();

    private RandomData() {

    }

    public static String firstName() {
        return faker.name().firstName();
    }

    public static String job() {
        return faker.job().title();
    }


}
