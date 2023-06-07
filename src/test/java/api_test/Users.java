package api_test;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.DataInput;
import java.io.IOException;

import static api_test.ObjectConverter.convertJsonToObject;

public class Users {
    static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test() {
        String userPayLoad = "{\n" +
                "    \"first_name\": \"Burulai\",\n" +
                "    \"email\": \"burulai@gmail.com\",\n" +
                "    \"id\": \"1234\"\n" +
                "}";

        String bmwPayLoad = "{\n" +
                "    \"model\": \"X6\",\n" +
                "    \"color\": \"black\",\n" +
                "    \"engine\": \"4.0\"\n" +
                "}";

        UsersPojo user = convertJsonToObject(userPayLoad, UsersPojo.class);
        System.out.println(user.getEmail());

        CarPojo2 bmw = convertJsonToObject(bmwPayLoad, CarPojo2.class);
        System.out.println(bmw.getColor());

    }



}
