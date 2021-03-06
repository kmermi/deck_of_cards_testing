package testcases;

import enums.Suit;
import enums.Value;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestBase {

    Response response;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://deckofcardsapi.com/api/deck/";
    }

    /*
There is a defect on the endpoint url https://deckofcardsapi.com/api/deck/<<deck_id>>/draw/?count=2
When we do not delete slash it will only draw one card so the correct usage should be
https://deckofcardsapi.com/api/deck/<<deck_id>>/draw?count=2
 */
    public Response drawCards(String deckId, int count, boolean withJoker) {
        response = given().pathParam("deck_id", deckId).queryParam("count", 52).when().get("{deck_id}/draw");
        response.then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                body("size()", is(4)).
                and().
                body("success", equalTo(true)).
                and().
                body("deck_id", equalTo(deckId));

        return response;
    }

    public Response createADeck(boolean jokersEnabled) {
        response = when().get("new");

        int expectedRemainingCards = jokersEnabled ? 54 : 52;
        response.then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                body("size()", is(4)).
                and().
                body("success", equalTo(true)).
                and().
                body("shuffled", equalTo(false)).
                and().
                body("remaining", equalTo(expectedRemainingCards));

        return response;
    }

    public void drawedCardComparison(Response response, int numberOfCardsToDraw) {
        int cardCounter = 0;

        while (numberOfCardsToDraw > 0) {
            for (Suit suit : Suit.values()) {
                for (Value value : Value.values()) {
                    String currentCode = value.getAbbreviatedName() + suit.getAbbreviatedName();
                    String cardValue = response.path("cards[" + cardCounter + "].value").toString();
                    String cardSuit = response.path("cards[" + cardCounter + "].suit").toString();
                    String cardCode = response.path("cards[" + cardCounter + "].code").toString();
                    Assert.assertEquals(value.getValueString(), cardValue);
                    Assert.assertEquals(suit.toString(), cardSuit);
                    Assert.assertEquals(currentCode, cardCode);
                    cardCounter++;
                    numberOfCardsToDraw--;
                }
            }
        }
    }
}
