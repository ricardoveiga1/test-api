package br.ricardo.suport.utils;

import br.ricardo.suport.apiClient.RestApi;
import br.ricardo.suport.domain.User;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DummyUtils {

    public static String getTokenUtils(){
        User user = new User();
        return given()
                .contentType("application/json")
                .body(user)
                .post("https://dummyjson.com/auth/login")
                .then().extract().path("accessToken");
    }

}
