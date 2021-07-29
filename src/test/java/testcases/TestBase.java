package testcases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;

public class TestBase {

    Response response;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://deckofcardsapi.com/api/deck/";
    }
}
