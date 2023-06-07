package api_practice.reqres;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Reqres {

    @Test
    public void base() {
        given()                       // дано
                .when()               // когда
                .then();              // тогда
                                      // После каждого ключевого слова вызываем какие-то  методы
    }

    @Test
    public void getUsers() {
        String res = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
//                .then().statusCode(200);    // statusCode - как Assert проверяет
                .body().asString();
                System.out.println(res);

    }

    @Test
    public void deserialization() {
        List<UserPractice> res = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200)    // statusCode - как Assert проверяет
                .extract().jsonPath().getList("data", UserPractice.class);
        System.out.println(res);
    }
}
