package com.digital.api;


import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


//@Data
public class ApiRequest {

    private static Logger logger = LogManager.getLogger(ApiRequest.class);
    protected String url;
    protected String apiKey;
    protected Headers headers;
    protected RequestSpecification specific;
    protected Response response;

    public ApiRequest(String url, String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
        this.specific = given()
                .auth()
                .basic(this.apiKey, "")
                .baseUri(this.url)
//                .headers(this.headers)
                .relaxedHTTPSValidation();

    }

    public int getStatusCode() {
        return this.response.getStatusCode();
    }


    public Response get(String endPoint){
        logger.info("Performed Get {}", endPoint);
        this.response = given()
                .spec(this.specific)
                .get(endPoint);
        logResponse();
        return this.response;
    }

    public Response get(String endPoint, Map<String, String> pathParam){
        logger.info("Performed Get {}", endPoint);
        this.response = given()
                .spec(this.specific)
                .pathParams(pathParam)
                .get(endPoint);
        logResponse();
        return this.response;
    }

    public Response post(String endPoint, String body) {
        logger.info("Performed POST {}", endPoint);
        logger.info("Body is {}", body);
        this.response = given()
                .spec(this.specific)
                .body(body)
                .post(endPoint);
        logResponse();
        return this.response;
    }

    public Response put(String endPoint, String body) {
        logger.info("Performed PUT {}", endPoint);
        logger.info("Body is {}", body);
        this.response = given()
                .spec(this.specific)
                .body(body)
                .put(endPoint);
        logResponse();
        return this.response;
    }

    public Response patch(String endPoint, String body) {
        logger.info("Performed PATCH {}", endPoint);
        logger.info("Body is {}", body);
        this.response = given()
                .spec(this.specific)
                .body(body)
                .patch(endPoint);
        logResponse();
        return this.response;
    }

    public Response delete(String endPoint) {
        this.response = given()
                .spec(this.specific)
                .delete(endPoint);
        logResponse();
        return this.response;
    }


    public void logResponse() {
        logger.warn("Response id {}", this.response.getBody().asPrettyString());
        logger.warn("Status code is: {}", this.response.getStatusCode());
    }

    public String generateEndPoints(String ...args) {
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

    public static String formatParameters(HashMap<String, String> parameters) {
        StringBuilder query = new StringBuilder("?");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            query.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return query.deleteCharAt(query.length() - 1).toString();
    }
}
