package com.digital.utils;

import com.digital.entities_pojo.User;

public class EntitiesManager {

    public static User genereteUser() {
        return User.builder()
                .firstName(FakeDataProvider.generateFirstName())
                .lastName(FakeDataProvider.generateLastName())
                .email(FakeDataProvider.generateEmail())
                .password(FakeDataProvider.generatePassword())
                .login(FakeDataProvider.generateLogin())
                .build();
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
