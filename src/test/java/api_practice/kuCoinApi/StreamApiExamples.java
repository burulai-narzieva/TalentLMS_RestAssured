package api_practice.kuCoinApi;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class StreamApiExamples {

    @Test
    public void checkCrypto() {
        List<TickerData> tickers = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then().log().body()
                .extract().jsonPath().getList("data.ticker", TickerData.class);            // extract - извлекать
        System.out.println(tickers);
        int a = 9;
    }

}
