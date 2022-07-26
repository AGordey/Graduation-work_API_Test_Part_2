package tests;

import lombok.MorfeusData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.AllureRestAssuredFilter.withCustomTemplates;
import static helpers.Specs.*;
import static io.restassured.RestAssured.given;
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

        assertEquals("morpheus",morfeus.getMorfeus().getName());
        assertEquals("zion resident", morfeus.getMorfeus().getJob());
        assertEquals(notNullValue(),morfeus.getMorfeus().getUpdatedAt());

    }

//    @Test
//    @DisplayName("Проверка информации юзера Rest Assured + lombok")
//    void checkSingleUserWithLombok() {
//
//        UserData data = given()
//                .baseUri("https://reqres.in/api/")
//                .when()
//                .get(SingleUser)
//                .then()
//                .log().body()
//                .extract().as(UserData.class);
//
//        assertEquals(2, data.getUser().getId());
//        assertEquals("janet.weaver@reqres.in", data.getUser().getEmail());
//        assertEquals("Janet", data.getUser().getFirstName());
//        assertEquals("Weaver", data.getUser().getLastName());
//    }
//
//    @Test
//    @DisplayName("Проверка информации юзера Rest Assured + lombok + спецификации")
//    void checkSingleUserWithLombokAndSpecification() {
//
//        UserData data = requestSpecsForGET
//                .when()
//                .get(SingleUser)
//                .then()
//                .spec(response200)
//                .log().body()
//                .extract().as(UserData.class);
//
//        assertEquals(2, data.getUser().getId());
//        assertEquals("janet.weaver@reqres.in", data.getUser().getEmail());
//        assertEquals("Janet", data.getUser().getFirstName());
//        assertEquals("Weaver", data.getUser().getLastName());
//    }
//
//    @Test
//    @DisplayName("Проверка информации юзера Rest Assured + lombok + спецификации + Allure Filter")
//    void checkSingleUserWithLombokAndSpecificationAndAllureFilter() {
//
//        UserData data = requestSpecsForGET
//                .filter(new AllureRestAssured())
//                .when()
//                .get(SingleUser)
//                .then()
//                .spec(response200)
//                .log().body()
//                .extract().as(UserData.class);
//
//        assertEquals(2, data.getUser().getId());
//        assertEquals("janet.weaver@reqres.in", data.getUser().getEmail());
//        assertEquals("Janet", data.getUser().getFirstName());
//        assertEquals("Weaver", data.getUser().getLastName());
//    }
//
//    @Test
//    @DisplayName("Проверка информации юзера Rest Assured + lombok + спецификации + Allure Filter Custom")
//    void checkSingleUserWithLombokAndSpecificationAndAllureFilterCustom() {
//
//        UserData data = requestSpecsForGET
//                .filter(withCustomTemplates())
//                .when()
//                .get(SingleUser)
//                .then()
//                .spec(response200)
//                .log().body()
//                .extract().as(UserData.class);
//
//        assertEquals(2, data.getUser().getId());
//        assertEquals("janet.weaver@reqres.in", data.getUser().getEmail());
//        assertEquals("Janet", data.getUser().getFirstName());
//        assertEquals("Weaver", data.getUser().getLastName());
//    }
//
//    @Test
//    @DisplayName("Проверка информации юзера Rest Assured + спецификации + Allure Filter Custom + Groovy")
//    void checkApiEndpoint2() {
//
//        requestSpecsForGET
//                .when()
//                .filter(withCustomTemplates())
//                .get(ListUser)
//                .then()
//                .spec(response200)
//                .log().body()
//                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
//                        hasItem("michael.lawson@reqres.in"))
//                .body("data.id.flatten()",
//                        hasItem(7));
//    }
}
