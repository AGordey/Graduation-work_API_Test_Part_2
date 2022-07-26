package tests;

public class Endpoints {

    public static final String SingleUser = "/users/2";
    public static final String ListUser = "/users?page=2";
    public static final String GETUser404 = "/users/23";
    public static final String RegisterUser = "/register";
    String BodyMain = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";
}
