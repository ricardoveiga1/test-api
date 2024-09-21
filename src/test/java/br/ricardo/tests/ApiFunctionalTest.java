package br.ricardo.tests;

import br.ricardo.Config;
import br.ricardo.context.SpringContextInit;
import br.ricardo.suport.apiClient.RestApi;
import br.ricardo.suport.domain.User;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = Config.class)
public class ApiFunctionalTest extends SpringContextInit {

    @Autowired
    private RestApi restApi;

    @Test
    public void validateApiTest(){
       Response response =  restApi.getSetup();
       response.getBody().prettyPrint();

       response.then().body("status", is("ok"));
       response.then().body("method", is("GET"));
    }

    @Test
    public void validateLoginAndToken(){
        Response response = restApi.getToken();
        response.getBody().prettyPrint();
        response.getBody().as(User.class);
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("accessToken", is(notNullValue()))
                .body("id", is(1))
                .body("username", is("emilys"));
    }

    @Test
    public void getCurrentUser(){
        Response response = restApi.getCurrentUser();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(1))
                .body("firstName", is("Emily"))
                .body("role", is("admin"));
    }

    @Test
    public void getAllProducts(){

        Response response = restApi.getAllProducts();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("products[0].id", is(1))
                .body("products[0].title", is("Essence Mascara Lash Princess"))
                .body("products[0].price", is(9.99F));
        //implemente mais validaççoes, olhar curso rest assured fundamentals
    }

    @Test
    public void addProduct(){

        Response response = restApi.addProduct();
        response.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", is(195))
                .body("title", is("BMW Pencil"));
    }

    @Test
    public void getProductId(){
        Response response = restApi.getProductId();
    }



}
