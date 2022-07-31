package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.ForCheckUserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.AllureRestAssuredFilter.withCustomTemplates;
import static helpers.Specs.requestSpecsForGet;
import static helpers.Specs.response200;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class WithAllStack extends Endpoints {

    @Test
    @DisplayName("Проверка информации юзера средствами Rest Assured")
    void checkSingleUser() {

        given()
                .when()
                .baseUri("https://reqres.in/api/")
                .get(singleUser)
                .then()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    @DisplayName("Проверка информации юзера средствами Rest Assured + спецификации")
    void checkSingleUserWithSpec() {

        requestSpecsForGet
                .when()
                .get(singleUser)
                .then()
                .spec(response200)
                .log().body()
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    @DisplayName("Проверка информации юзера Rest Assured + lombok")
    void checkSingleUserWithLombok() {

        ForCheckUserData data = given()
                .baseUri("https://reqres.in/api/")
                .when()
                .get(singleUser)
                .then()
                .log().body()
                .extract().as(ForCheckUserData.class);

        assertEquals(2, data.getUser().getId());
        assertEquals("janet.weaver@reqres.in", data.getUser().getEmail());
        assertEquals("Janet", data.getUser().getFirstName());
        assertEquals("Weaver", data.getUser().getLastName());
    }

    @Test
    @DisplayName("Проверка информации юзера Rest Assured + lombok + спецификации")
    void checkSingleUserWithLombokAndSpecification() {

        ForCheckUserData data = requestSpecsForGet
                .when()
                .get(singleUser)
                .then()
                .spec(response200)
                .log().body()
                .extract().as(ForCheckUserData.class);

        assertEquals(2, data.getUser().getId());
        assertEquals("janet.weaver@reqres.in", data.getUser().getEmail());
        assertEquals("Janet", data.getUser().getFirstName());
        assertEquals("Weaver", data.getUser().getLastName());
    }

    @Test
    @DisplayName("Проверка информации юзера Rest Assured + lombok + спецификации + Allure Filter")
    void checkSingleUserWithLombokAndSpecificationAndAllureFilter() {

        ForCheckUserData data = requestSpecsForGet
                .filter(new AllureRestAssured())
                .when()
                .get(singleUser)
                .then()
                .spec(response200)
                .log().body()
                .extract().as(ForCheckUserData.class);

        assertEquals(2, data.getUser().getId());
        assertEquals("janet.weaver@reqres.in", data.getUser().getEmail());
        assertEquals("Janet", data.getUser().getFirstName());
        assertEquals("Weaver", data.getUser().getLastName());
    }

    @Test
    @DisplayName("Проверка информации юзера Rest Assured + lombok + спецификации + Allure Filter Custom")
    void checkSingleUserWithLombokAndSpecificationAndAllureFilterCustom() {

        ForCheckUserData data = requestSpecsForGet
                .filter(withCustomTemplates())
                .when()
                .get(singleUser)
                .then()
                .spec(response200)
                .log().body()
                .extract().as(ForCheckUserData.class);

        assertEquals(2, data.getUser().getId());
        assertEquals("janet.weaver@reqres.in", data.getUser().getEmail());
        assertEquals("Janet", data.getUser().getFirstName());
        assertEquals("Weaver", data.getUser().getLastName());
    }

    @Test
    @DisplayName("Проверка информации юзера Rest Assured + спецификации + Allure Filter Custom + Groovy")
    void checkApiEndpoint2() {

        requestSpecsForGet
                .when()
                .filter(withCustomTemplates())
                .get(listUser)
                .then()
                .spec(response200)
                .log().body()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("michael.lawson@reqres.in"))
                .body("data.id.flatten()",
                        hasItem(7));
    }
}
