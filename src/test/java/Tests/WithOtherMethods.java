package Tests;

import Models.MorfeusAnswer;
import Models.MorfeusBody;
import Models.UserWithEmailAndPassword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.AllureRestAssuredFilter.withCustomTemplates;
import static helpers.Specs.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class WithOtherMethods extends Endpoints {

    //    String morfeus = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";
    MorfeusBody morfeus = new MorfeusBody("morpheus", "zion resident");

    @Test
    @DisplayName("Проверка создания юзера с методом POST")
    void checkCreateUserPOSTMethod() {
//        String user2 = "{ \"email\": \"\", \"password\": \"pistol\" }";
        UserWithEmailAndPassword userBodyEmailAndPassword = new UserWithEmailAndPassword("eve.holt@reqres.in", "pistol");

        requestSpecsForPost
                .log().uri()
                .when()
                .filter(withCustomTemplates())
                .body(userBodyEmailAndPassword)
                .post(registerUser)
                .then()
                .spec(response200)
                .body("id", is(4))
                .body("token", is(notNullValue()));
    }

    @Test
    @DisplayName("Проверка обновления юзера с методом PUT")
    void checkUpdateUserWithPUT() {

        MorfeusAnswer morfeus = requestSpecsForPost
                .when()
                .filter(withCustomTemplates())
                .body(this.morfeus)
                .put(singleUser)
                .then()
                .spec(response200)
                .log().body()
                .extract().as(MorfeusAnswer.class);

        assertEquals("morpheus", morfeus.getName());
        assertEquals("zion resident", morfeus.getJob());
        assertNotNull(morfeus.getUpdatedAt());
//        assertThat(morfeus.getUpdatedAt(), is(notNullValue()));
    }

    @Test
    @DisplayName("Проверка обновления юзера с методом PATCH")
    void checkUpdateUserWithPATCH() {

        MorfeusAnswer morfeus = requestSpecsForPost
                .when()
                .filter(withCustomTemplates())
                .body(this.morfeus)
                .patch(singleUser)
                .then()
                .spec(response200)
                .log().body()
                .extract().as(MorfeusAnswer.class);

        assertEquals("morpheus", morfeus.getName());
        assertEquals("zion resident", morfeus.getJob());
        assertNotNull(morfeus.getUpdatedAt());
    }

    @Test
    @DisplayName("Проверка удаления юзера с методом DELETE")
    void checkDeleteUserWithDELETE() {

        requestSpecsForPost
                .when()
                .filter(withCustomTemplates())
                .delete(singleUser)
                .then()
                .spec(response204);
    }

}
