package utils;

import com.github.javafaker.Faker;
import models.UserCreateRequest;

import java.util.Random;

public class UserGenerator {

    private String name;
    private String email;
    private String gender;
    private String status;

    private String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public UserGenerator() {
        String[] GENDER = {"male", "female"};
        String[] STATUS = {"active", "inactive"};

        Faker faker = new Faker();
        name = faker.name().fullName();
        email = faker.internet().emailAddress();
        gender = getRandom(GENDER);
        status = getRandom(STATUS);
    }

    public UserCreateRequest generateUserRequest() {
        return UserCreateRequest.builder()
                .name(name)
                .email(email)
                .gender(gender)
                .status(status)
                .build();
    }
}
