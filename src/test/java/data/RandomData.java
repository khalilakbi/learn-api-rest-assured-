package data;

import com.github.javafaker.Faker;

public class RandomData {

    static Faker faker = new Faker();
   // cant create instance(object) of this class
    private RandomData() {

    }

    public static String firstName() {
        return faker.name().firstName();

    }

    public static String job() {
        return faker.job().title();
    }
    public static String password(){
        return faker.internet().password(8,16)+ "T@2";
    }


}
