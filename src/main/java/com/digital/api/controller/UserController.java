package com.digital.api.controller;

import com.digital.api.ApiRequest;
import static com.digital.api.BaseEndPoints.*;
import com.digital.api.entities.User;
import groovy.json.JsonOutput;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;

public class UserController extends ApiRequest {
    public UserController(String url, String apiKey) {
        super(url, apiKey);
    }

    public User[] receiveUsers() {
        return super.get(generateEndPoints(API, V1, USERS)).as(User[].class);
    }

    public User receiveSingleUsers( String by, String idOrEmail) {
        HashMap <String, String> map = new HashMap<>(){{
            put(by, idOrEmail);
        }};
        get(generateEndPoints(API, V1, USERS, formatParameters(map))).as(User.class);
        return new User();
    }

    public User receiveSingleUsers1( String by, String idOrEmail) {
        get(generateEndPoints(API, V1, USERS ), pathParam(by, idOrEmail));
        User[] users = receiveUsers();
        for(User user : users) {
            if(user.getId().equalsIgnoreCase(idOrEmail) || user.getEmail().equalsIgnoreCase(idOrEmail)) {
                return user;
            }
        }
        return null;
    }


    public Response deleteUser() {
        return super.delete(generateEndPoints(API, V1, USERS, "id:1"));
    }


    public static void main(String[] args) {
        UserController userController = new UserController("https://burulai.talentlms.com", "5pgR9B2XNtyprXnbAuXFgR17mQ4EHs");
//        System.out.println(userController.receiveUsers());
//
//        User[] users = userController.receiveUsers();
//        Arrays.stream(users).forEach(user ->
//                        System.out.println(user.getFirstName())
//
//                );
//        System.out.println(userController.receiveUsers()[0].getFirstName());
//        Assert.assertEquals(200, userController.getStatusCode());
//
//        System.out.println(userController.deleteUser());


        userController.receiveSingleUsers1("email", "burulainarzieva2@gmail.com");


    }
}
