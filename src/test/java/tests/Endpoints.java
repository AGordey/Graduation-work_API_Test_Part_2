package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class Endpoints {

    public static final String GETSingleUser = "/users/2";
    public static final String GETListUser = "/users?page=2";
    public static final String GETUser404 = "/users/23";
}
