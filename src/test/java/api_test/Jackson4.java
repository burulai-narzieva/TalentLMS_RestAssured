package api_test;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static api_test.ObjectConverter.convertJavaClassToJson;
import static api_test.ObjectConverter.convertJsonToCarClass;

public class Jackson4 {

    public static void main(String[] args) throws JsonProcessingException {

        //
        ObjectMapper objectMapper  = new ObjectMapper();

        String bmwPayLoad = "{\n" +
                "    \"model\": \"X6\",\n" +
                "    \"color\": \"black\",\n" +
                "    \"engine\": \"4.0\"\n" +
                "}";

//        // readValue:  JSON --> Java
//        CarPojo2 bmw = objectMapper.readValue(bmwPayLoad, CarPojo2.class);
//        System.out.println(bmw.getModel());
//
//        CarPojo2 audi = new CarPojo2("A^", "red", "2.4");
//
//
//        // writeValueAsString:  Java --> JSON
//        String audiPayload = objectMapper.writeValueAsString(audi);
//        System.out.println(audiPayload);


        CarPojo2 audi2 = new CarPojo2("A^", "red", "2.4");

        CarPojo2 bmv2 = convertJsonToCarClass(bmwPayLoad);
        System.out.println(bmv2.getModel());

        String audiPayload2 = convertJavaClassToJson(audi2);
        System.out.println(audiPayload2);


    }

}
