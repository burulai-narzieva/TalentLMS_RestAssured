package com.digital.api.controller;

import com.digital.api.ApiRequest;
import static com.digital.api.BaseEndPoints.*;
import com.digital.entities.User;
import com.digital.utils.EntitiesManager;
import com.digital.utils.JsonUtils;
import io.restassured.response.Response;

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

    public User createUser(User user) {
        String userJson = JsonUtils.convertJavaObjectToJson(user);
        return super.post(generateEndPoints(API, V1, SIGNUP), userJson).as(User.class);
    }

    public Response userDelete(String id) {
        return super.post(generateEndPoints(API,V1,DELETE_USER), "user_id="+id);
    }



//    public User receiveSingleUsers1( String by, String idOrEmail) {
//        get(generateEndPoints(API, V1, USERS ), pathParam(by, idOrEmail));
//        User[] users = receiveUsers();
//        for(User user : users) {
//            if(user.getId().equalsIgnoreCase(idOrEmail) || user.getEmail().equalsIgnoreCase(idOrEmail)) {
//                return user;
//            }
//        }
//        return null;
//    }



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


//        userController.receiveSingleUsers("email", "burulainarzieva2@gmail.com");

        User user = EntitiesManager.genereteUser();
        userController.createUser(user);


    }
}
