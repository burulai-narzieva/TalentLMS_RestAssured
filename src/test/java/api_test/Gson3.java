package api_test;

import com.google.gson.Gson;

public class Gson3 {
    public static void main(String[] args) {
        String bmwPayLoad = "{\n" +
                "    \"model\": \"X6\",\n" +
                "    \"color\": \"black\",\n" +
                "    \"engine\": \"4.0\"\n" +
                "}";

        // Для корвертации создоем  Gson класс

        Gson gson = new Gson();

        // Десериализация
        // fromJson:         JSON --> Java
        CarPojo2 carPojo2 = gson.fromJson(bmwPayLoad, CarPojo2.class);
        System.out.println(carPojo2.getModel());


        CarPojo2 tesla = new CarPojo2("Model s","white","5.00");

        // Сериализация
        //  toJson:          Java --> JSON
        String teslaPayload = gson.toJson(tesla);
        System.out.println(teslaPayload);
    }
}







