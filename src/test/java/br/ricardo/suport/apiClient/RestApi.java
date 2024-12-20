package br.ricardo.suport.apiClient;


import br.ricardo.properties.ApplicationConfig;
import br.ricardo.suport.domain.User;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.ricardo.suport.utils.DummyUtils.getTokenUtils;
import static io.restassured.RestAssured.given;

@Component
public class RestApi {

    @Autowired
    ApplicationConfig properties;

    public Response getSetup(){
        final var request = given().filter(new AllureRestAssured())
                .contentType("application/json");

        final var response = request.get(properties.getUrlBase() + "/test");

        response.then()
                .onFailMessage("Request get application status failed")
                .statusCode(HttpStatus.SC_OK);

        return response;
    }

    public Response getUser(){
        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .contentType("application/json")
                        .get(properties.getUrlBase() + properties.getUsers());

        response
                .then()
                .onFailMessage("Failed to get users")
                .statusCode(HttpStatus.SC_OK);

        return response;
    }

    public Response getToken(){
        User user = new User();

        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .contentType("application/json")
                        .body(user)
                        .post(properties.getUrlBase() + properties.getLogin());

        response
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK);

        return response;
    }

    public Response getCurrentUser(){
        //final var token = getToken().then().extract().path("accessToken");
        final var token = getTokenUtils();

        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + token)
                        .header("credentials", "include")
                        .get(properties.getUrlBase() + properties.getGetCurrentUser());

        response.prettyPrint();
        response
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK);

        return response;
    }

    public Response getAllProducts(){

        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .contentType("application/json")
                        .get(properties.getUrlBase() + properties.getGetAllProducts());

        response.prettyPrint();
        response
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK);

        return response;
    }

    public Response addProduct(){

        String bodyProduct = "{\"title\": \"BMW Pencil\"}";

        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .contentType("application/json")
                        .body(bodyProduct)
                        .post(properties.getUrlBase() + properties.getAddProduct());

        response.prettyPrint();
        response
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED);

        return response;
    }

    public Response getProductId(Integer idProduct){

        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .contentType("application/json")
                        .pathParams("idProduct", idProduct)
                        .get(properties.getUrlBase() + properties.getGetSingleProduct());

        response.prettyPrint();
        response
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK);

        return response;
    }
}
