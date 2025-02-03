package br.ricardo.suport.apiClient;


import br.ricardo.properties.ApplicationConfig;
import br.ricardo.suport.domain.User;
import br.ricardo.suport.domain.products.Product;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Log4j2
@Component
public class RestApi {

    @Autowired
    ApplicationConfig properties;

    public Response getAppStatus(){
        log.warn("Request get application status has been started!");
        final var request = given().filter(new AllureRestAssured())
                .contentType(ContentType.JSON);

        final var response = request.get(properties.getUrlBase() + "/test");
        response.then()
                .onFailMessage("Request get application status failed with status code: " + + response.statusCode())
                .statusCode(HttpStatus.SC_OK);
        return response;
    }

    public Response getUsers(){
        log.warn("Request get application users has been started!");
        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .contentType(ContentType.JSON)
                        .get(properties.getUrlBase() + properties.getUsers());

        response
                .then()
                .onFailMessage("Failed to get users with status code: " + response.statusCode())
                .statusCode(HttpStatus.SC_OK);
        return response;
    }

    public Response appLogin(User user){
        //User user = new User();
        log.warn("Request application login has been started!");
        final var request = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(user);

        final var response = request.post(properties.getUrlBase() + properties.getAuthLogin());
        response.then().contentType(ContentType.JSON).statusCode(HttpStatus.SC_OK);
        return response;
    }

    public Response getCurrentProducts(String token){
        log.warn("Request user logged products has been started!");
        final var request = given()
                        .filter(new AllureRestAssured())
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer " + token);
                        //.header("credentials", "include");

        return request.get(properties.getUrlBase() + properties.getCurrentProducts());
    }

    public Response getAllProducts(){
        log.warn("Request user logged products has been started!");
        final var request = given()
                        .filter(new AllureRestAssured())
                        .contentType(ContentType.JSON);

        return request.get(properties.getUrlBase() + properties.getAllProducts());
    }

    public Response addProduct(Product product){
        log.warn("Request user logged products has been started!");
        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .contentType(ContentType.JSON)
                        .body(product)
                        .post(properties.getUrlBase() + properties.getAddProduct());

        response.prettyPrint();
        response.then()
                .onFailMessage("Request get application users failed with status code: " + response.statusCode())
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED);

        return response;
    }

    public Response getProductId(int idProduct){
        log.warn("Request user logged products has been started!");
        final var request = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .pathParams("idProduct", idProduct);
        return request.get(properties.getUrlBase() + properties.getSingleProduct());
    }
}
