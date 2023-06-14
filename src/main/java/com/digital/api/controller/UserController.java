package com.digital.api.controller;

import com.digital.api.ApiRequest;
import static com.digital.api.BaseEndPoints.*;
import static io.restassured.RestAssured.given;

import com.digital.entities_pojo.User;
import com.digital.utils.EntitiesManager;
import com.digital.utils.JsonUtils;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;

public class UserController extends ApiRequest {
    public UserController(String baseUrl, String apiKey) {
        super(baseUrl, apiKey);
    }


    public User[] receiveUsers() {
        return super.get(generateEndPoints(API, V1, USERS)).as(User[].class);
        // given();  // alt + enter
    }

    public User receiveSingleUserWithQuery( String by, String idOrEmail) {
        HashMap <String, String> map = new HashMap<>(){{
            put(by, idOrEmail);
        }};
        get(generateEndPoints(API, V1, USERS, queryParam(map))).as(User.class);
        return new User();
    }

    public User receiveSingleUserWithPath( String by, String idOrEmail) {
        get(generateEndPoints(API, V1, USERS ), pathParam(by, idOrEmail));
        User[] users = receiveUsers();
        for(User user : users) {
            if(user.getId().equalsIgnoreCase(idOrEmail) || user.getEmail().equalsIgnoreCase(idOrEmail)) {
                return user;
            }
        }
        return null;
    }

    public Response editUser() {
        return super.post(generateEndPoints(API, V1, EDIT_USER), "user_id=2&first_name=Aang");
    }



    public User createUser(User user) {
        String userJson = JsonUtils.convertJavaObjectToJson(user);
        return super.post(generateEndPoints(API, V1, SIGNUP), userJson).as(User.class);
    }

    public Response userDelete(String id) {
        return super.post(generateEndPoints(API,V1,DELETE_USER), "user_id=" + id);
    }

    public Response userSignUp() {
        String userData = JsonUtils.convertJavaObjectToJson(EntitiesManager.genereteUser());
        return super.post(generateEndPoints(API, V1, SIGNUP), userData);
    }

    public Response userLogin () {
        return super.post(generateEndPoints(API, V1, USER_LOGIN), "login=Hatiko&password=ZarinaZarina2");
    }

//    public Response userLogOut(String by, String id) {
//        HashMap <String, String> map = new HashMap<>(){{ put(by, id); }};
////        return super.post(generateEndPoints(API, V1, USER_LOGOUT, queryParam(map)));
//    }

    public Response isUserOnline(String by, String id) {
        HashMap <String, String> map = new HashMap<>(){{ put(by, id); }};
        return super.get(generateEndPoints(API, V1, IS_USER_ONLINE, queryParam(map)));
    }













    public static void main(String[] args) {
        UserController userController = new UserController("https://burulai.talentlms.com", "5pgR9B2XNtyprXnbAuXFgR17mQ4EHs");
//        System.out.println(userController.receiveUsers());
//        // Мы здесь получили респонс, полный объект. Но чтобы получить доступ к каждому полю бади
//        // мы используем Gson или Jackson. Во многих сучиях будем использовать Jackson,
//        // и для этого (сериализация/десериализация) открываем POJO класс User
//        Assert.assertEquals(200, userController.getStatusCode());
//
//
//        User[] users = userController.receiveUsers();
//
//        System.out.println(users[0].getFirstName());               // 1
//        Arrays.stream(users).forEach(user ->                       // 2
//                        System.out.println(user.getFirstName())
//        );

//        **************************



//        User user3 = userController.receiveSingleUserWithQuery("email", "burulainarzieva2@gmail.com");
//        System.out.println("SINGLE USER" + user3.getId());

//        System.out.println(userController.createUser(EntitiesManager.genereteUser()));

//        User user4 = userController.receiveSingleUserWithPath("email", "burulainarzieva2@gmail.com");
//        System.out.println("SINGLE USER" + user4);


//        ***************

        User user = EntitiesManager.genereteUser();
        userController.createUser(user);
//
//        System.out.println(userController.editUser());
//        System.out.println(userController.userDelete("2"));
//
//        System.out.println(userController.userSignUp());

//        System.out.println(userController.userLogin());

//        System.out.println(userController.userLogOut("user_id", "10"));
//
//        System.out.println(userController.isUserOnline("user_id", "7"));


    }
}
