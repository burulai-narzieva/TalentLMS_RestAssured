package com.digital.api;


import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


@Slf4j
@Data     //
public class ApiRequest {

    // alt + наводить правой кнопкой мыши
    protected String url;                         // адрес
    protected String apiKey;                      // для авторизации
    protected Headers headers;                    // настройки: контен-тайп....
    protected RequestSpecification specific;      // основной интерфейс рест ашурда для отправки запросов
    protected Response response;                  // получаем ответы наших запросов

    public ApiRequest(String url, String apiKey) {
        this.url = url;                                         // инициализируется базовый юрл
        this.apiKey = apiKey;                                   // инициализируется апиКлюч
        this.specific = given()                                 // инициализируется настройка нашего запроса
                .auth()                                         // передаем авторизацию
                .basic(this.apiKey, "")                      // авторизацию - именно basic(апиКлюч, пароль) - из посмана посмотрим
                .baseUri(this.url)                              // передаем baseUrl
//                .headers(this.headers)                        // передадим наши хедери
                .relaxedHTTPSValidation();                      // по HTTPS будет отправляться

    }

    public int getStatusCode() {
        return this.response.getStatusCode();
    }


    public Response get(String endPoint){         // для гет запроса нам нужно авторизация, хеадерс, и эндпойнтс.
                                                  // Мы уже инициализ. авторизацию и передали  хеадерс , остоется эндпойнтс
        log.info("Performed GET {}", endPoint);
        this.response = given()
                .spec(this.specific)
                .get(endPoint);
        logResponse();
        return this.response;
    }

    public Response get(String endPoint, Map<String, String> pathParam){
        log.info("Performed GET {}", endPoint);
        this.response = given()
                .spec(this.specific)
                .pathParams(pathParam)
                .get(endPoint);
        logResponse();
        return this.response;
    }

//    public Response post(String endPoint, String body) {
//        log.info("Performed POST {}", endPoint);
//        log.info("Body is {}", body);
//        this.response = given()
//                .spec(this.specific)
//                .body(body)
//                .post(endPoint);
//        logResponse();
//        return this.response;
//    }

    public Response post(String endpoint, String body){
        log.info("Performed POST: {}", endpoint);
        log.info("Body is: {}", body);
        this.response = given()
                .contentType(ContentType.URLENC)     // принимеат form-data из  body
                .spec(this.specific)
                .body(body)
                .post(endpoint);
        logResponse();
        return this.response;
    }

    public Response put(String endPoint, String body) {
        log.info("Performed PUT {}", endPoint);
        log.info("Body is {}", body);
        this.response = given()
                .spec(this.specific)
                .body(body)
                .put(endPoint);
        logResponse();
        return this.response;
    }

    public Response patch(String endPoint, String body) {
        log.info("Performed PATCH {}", endPoint);
        log.info("Body is {}", body);
        this.response = given()
                .spec(this.specific)
                .body(body)
                .patch(endPoint);
        logResponse();
        return this.response;
    }



    public void logResponse() {
        log.warn("Response id {}", this.response.getBody().asPrettyString());  // получаем бади нашего запроса и возвращаем как стринг
        log.warn("Status code is: {}", this.response.getStatusCode());
    }

    public static String generateEndPoints(String... args) {
        StringBuilder result = new StringBuilder();
        for(String arg : args) {
            result.append(arg).append("/");
        }
        return result.substring(0, result.length() -1);
    }

    public Map<String, String> pathParam(String key, String value) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>() {{
            put(key,value);
        }};
        return linkedHashMap;
    }

    public static String queryParam(HashMap<String, String> parameters) {
        StringBuilder query = new StringBuilder("?");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            query.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return query.deleteCharAt(query.length() - 1).toString();
    }


//    public static void main(String[] args) {
//        String format  = String.format("Response is : %S", "lalalala");
//        System.out.println(format);
//    }
}
