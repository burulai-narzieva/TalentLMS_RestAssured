package api_test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class Demo1 {

    public static Faker faker = new Faker();

    @Test
    public void demoTest1() {

        // В RequestSpecification можем передать все настройки из Headers
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri("https://gorest.co.in/public/v2/users")
                .contentType(ContentType.JSON)     // в каком формате отправляем на сервер
                .accept(ContentType.JSON);        // в каком формате получаем из сервера

//        requestSpecification.queryParam("id", "2557955");  // query
//        Response response = requestSpecification.request(Method.GET);

        requestSpecification.pathParam("id", "1");    // path param
        Response response = requestSpecification.request(Method.GET, "/{id}");   // чтобы получить ответы нашего запроса

        response.prettyPrint();
        System.out.println(response.getStatusCode());
        System.out.println(response.getContentType());

    }

    public String randomStatus(){
        String [] genders = {"active","inactive"};
        int randomIndex = (int) (Math.random() * genders.length);
        return genders[randomIndex];
    }


    @Test
    public void createNewUserTest() {

        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String gender = faker.demographic().sex();

        String userPayload = "    {\n" +
                "        \"name\": \"" + name + "\",\n" +
                "        \"email\": \"" + email + "\",\n" +
                "        \"gender\": \""+ gender + "\",\n" +
                "        \"status\": \"" + randomStatus() +"\"\n" +
                "    }";
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://gorest.co.in");
        requestSpecification.header("Authorization", "Bearer 709021d5a7d6c6c17f7ade19c688d5d3cbf4f7581083689bee7b163f5cdad47c");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);
        requestSpecification.body(userPayload);

        Response response = requestSpecification.request(Method.POST, "/public/v2/users");
        response.prettyPrint();


        Assert.assertEquals(response.getStatusCode(), 201);


    }


    @Test
    public void jsonPathTest() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://reqres.in/api/users");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);

        Response response = requestSpecification.request(Method.GET);


        // JSONPATH  -  дает доступ к каждому полю ответа
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath);
//        int totalUsers = jsonPath.getInt("total");
//        System.out.println(totalUsers);


        String name = jsonPath.getString("data[1].id");
        System.out.println(name);

    }

}
