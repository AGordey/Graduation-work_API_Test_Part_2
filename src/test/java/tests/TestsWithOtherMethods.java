package tests;

import lombok.Morfeus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.AllureRestAssuredFilter.withCustomTemplates;
import static helpers.Specs.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestsWithOtherMethods extends Endpoints {

    String BodyMain = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

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
    @DisplayName("Проверка обновления пользователяс методом PUT")
    void checkUpdateUserWithPUT() {

        Morfeus morfeus = requestSpecsForPOST
                .when()
                .filter(withCustomTemplates())
                .body(BodyMain)
                .put(SingleUser)
                .then()
                .spec(response200)
                .log().body()
                .extract().as(Morfeus.class);

        assertEquals("morpheus", morfeus.getName());
        assertEquals("zion resident", morfeus.getJob());
        assertNotNull(morfeus.getUpdatedAt());
//        assertThat(morfeus.getUpdatedAt(), is(notNullValue()));
    }

    @Test
    @DisplayName("Проверка обновления пользователяс методом PUT")
    void checkUpdateUserWithPATCH() {

        Morfeus morfeus = requestSpecsForPOST
                .when()
                .filter(withCustomTemplates())
                .body(BodyMain)
                .patch(SingleUser)
                .then()
                .spec(response200)
                .log().body()
                .extract().as(Morfeus.class);

        assertEquals("morpheus", morfeus.getName());
        assertEquals("zion resident", morfeus.getJob());
        assertNotNull(morfeus.getUpdatedAt());

    }

    @Test
    @DisplayName("Проверка удаления пользователя с методом DELETE")
    void checkDeleteUserWithDELETE() {

        requestSpecsForPOST
                .when()
                .filter(withCustomTemplates())
                .delete(SingleUser)
                .then()
                .spec(response204);
    }

}
