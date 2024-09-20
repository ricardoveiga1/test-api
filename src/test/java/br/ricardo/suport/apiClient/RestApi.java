package br.ricardo.suport.apiClient;


import br.ricardo.properties.ApplicationConfig;
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

    public Response getToken(){}




}
