package api_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;

public class ObjectConverter {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static CarPojo2 convertJsonToCarClass(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, CarPojo2.class);
    }
    public static String convertJavaClassToJson(Object object) throws JsonProcessingException {
        String result = objectMapper.writeValueAsString(object);
        return result;
    }

    public static <T> T convertJsonToObject(String json, Class<T> objectType) {
        try {
            return objectMapper.readValue(json, objectType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
