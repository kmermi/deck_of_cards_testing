package testcases;

import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class DeckTest extends TestBase {

    @Test
    public void createADeckWithoutJockerGet() {

        response = createADeck(false);

        response.then().
                assertThat().
                body("size()", is(4)).
                and().
                body("success", equalTo(true)).
                and().
                body("shuffled", equalTo(false)).
                and().
                body("remaining", equalTo(52));

        String deckId = response.path("deck_id");
        int cardsToDraw = 52;
        response = drawCards(deckId, cardsToDraw, false);

        response.then().
                assertThat().
                body("remaining", equalTo(0));

        drawedCardComparison(response, cardsToDraw);
    }


/*
    RestAssured does not support redirect with 301 codes but supports 303
    https://stackoverflow.com/questions/42480929/how-to-follow-the-redirect-in-rest-assured
    Need to try a workout if possible
 */
    @Test
    public void createADeckWithJockerPost() {

    }

    @Test
    public void drawOneCardFromTheDeck() {
        response = createADeck(false);

        String deckId = response.path("deck_id");
        int cardsToDraw = 1;
        response = drawCards(deckId, cardsToDraw, false);

        response.then().
                assertThat().
                body("remaining", equalTo(0));

        drawedCardComparison(response, cardsToDraw);
    }







}
