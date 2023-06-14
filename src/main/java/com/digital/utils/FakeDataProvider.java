package com.digital.utils;

import com.github.javafaker.Faker;

public class FakeDataProvider {

  public static Faker faker = new Faker();

    public static String generateFirstName(){return faker.name().firstName();}
    public static String generateLastName() {
        return faker.name().lastName();
    }
    public static String generateEmail() {
        return faker.internet().emailAddress();
    }
    public static String generatePassword() {
        return faker.internet().password(15, 17, true, true, true) + ".";
    }
    public static String generateLogin() {
        return faker.name().username();
    }



    public static void main(String[] args) {
        System.out.println(generateFirstName());
    }


}
