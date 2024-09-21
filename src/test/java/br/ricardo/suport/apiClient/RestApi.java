package br.ricardo.suport.apiClient;


import br.ricardo.properties.ApplicationConfig;
import br.ricardo.suport.dto.User;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Component
public class RestApi {



    @Autowired
    ApplicationConfig properties;

    public Response getSetup(){
        final var request = given()
                .contentType("application/json");

        final var response = request.get(properties.getUrlBase() + "/test");

        response.then()
                .onFailMessage("Request get application status failed")
                .statusCode(HttpStatus.SC_OK);

        return response;
    }

    public Response loginAndGetToken(){
        User userBody = new User("emilys", "emilyspass", 30);
        given()
                .body(userBody)
                .when()
                .post(properties.getUrlBase() + properties.getLOGIN())

//        final var request = given()
//                .log().all()
//                .contentType("application/json")/
//                .body(userBody);
//
//        final var response = request.post(properties.getUrlBase() + properties.getLOGIN());
//
//        response.then().log().all()
//                .onFailMessage("Request post application login and token")
//                .statusCode(HttpStatus.SC_OK);
//
//        return response;

    }




}
