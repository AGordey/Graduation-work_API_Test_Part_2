package tests;

import lombok.MorfeusData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.AllureRestAssuredFilter.withCustomTemplates;
import static helpers.Specs.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestsWithOtherMethods extends Endpoints {

    @Test
    @DisplayName("Проверка создания пользователя с методом POST")
    void checkCreateUserPOSTMethod() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        requestSpecsForPOST
                .log().uri()
                .when()
                .filter(withCustomTemplates())
                .body(body)
                .post(RegisterUser)
                .then()
                .spec(response200)
                .body("id", is(4))
                .body("token", is(notNullValue()));
    }

    @Test
    @DisplayName("Проверка с методом PUT")
    void checkUpdateUserWithPUT() {

        MorfeusData morfeus = requestSpecsForPOST
                .when()
                .filter(withCustomTemplates())
                .body(BodyMain)
                .put(SingleUser)
                .then()
                .spec(response200)
                .log().body()
                .extract().as(MorfeusData.class);

        assertEquals("morpheus", morfeus.getMorfeus().getName());
        assertEquals("zion resident", morfeus.getMorfeus().getJob());
        assertEquals(notNullValue(), morfeus.getMorfeus().getUpdatedAt());

    }

}
