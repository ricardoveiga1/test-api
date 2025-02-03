package br.ricardo.tests;

import br.ricardo.Config;
import br.ricardo.context.SpringContextInit;
import br.ricardo.suport.apiClient.RestApi;
import br.ricardo.suport.domain.products.Product;
import br.ricardo.suport.domain.User;
import br.ricardo.suport.domain.Users;
import br.ricardo.suport.domain.products.Products;
import br.ricardo.suport.utils.DummyUtils;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = Config.class)
public class ApiFunctionalTest extends SpringContextInit {
    private Users users;
    private Products products;
    @Autowired
    private RestApi restApi;
    @Autowired
    private DummyUtils utils;

    @DisplayName("Validate application status")
    @Test
    public void shouldValidateApiTest() {
        Response response = restApi.getAppStatus();
        response.getBody().prettyPrint();
        response.then().time(lessThan(2000L));

        response.then().body("status", is("ok"));
        response.then().body("method", is("GET"));
    }

    @DisplayName("Validate application users")
    @Test
    public void shouldGetUsers() {
        Response response = restApi.getUsers();
        users = response.getBody().as(Users.class);
        response.getBody().prettyPrint();

        users.getUsers().forEach(user -> {
            Assertions.assertNotNull(user.getUsername());
            Assertions.assertNotNull(user.getPassword());
        });
    }


    @DisplayName("Validate application login")
    @Test
    public void shouldValidateLoginAndToken() {
        User user = User.builder()
                .username("emilys")
                .password("emilyspass")
                .build();

        Response response = restApi.appLogin(user);
        response.getBody().prettyPrint();
        response.getBody().as(User.class);
        response.then()
                .statusCode(HttpStatus.SC_OK)
                //contract test
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("loginToken.json"))
                .body("accessToken", is(notNullValue()))
                .body("id", is(1))
                .body("username", is("emilys"));

        Assertions.assertNotNull(response.then().body("id", notNullValue()));
        Assertions.assertNotNull(response.then().body("email", notNullValue()));
        response.then().body("username", is("emilys"));
        Assertions.assertNotNull(response.then().body("firstName", notNullValue()));
        Assertions.assertNotNull(response.then().body("lastName", notNullValue()));
        Assertions.assertNotNull(response.then().body("gender", notNullValue()));
        Assertions.assertNotNull(response.then().body("image", notNullValue()));
        Assertions.assertNotNull(response.then().body("accessToken", notNullValue()));
        Assertions.assertNotNull(response.then().body("refreshToken", notNullValue()));

    }

    @DisplayName("Get application user logged")
    @Test
    public void shouldGetProductLogged() {
        User user = User.builder()
                .username("emilys")
                .password("emilyspass")
                .build();

        String token = utils.getUserToken(user).getToken();
        Response response = restApi.getCurrentProducts(token);
        response.then().log().all().statusCode(HttpStatus.SC_OK);

        products = response.getBody().as(Products.class);
        utils.validateProducts(products);
    }

    @DisplayName("Get all application products")
    @Test
    public void shouldGetAllProducts() {
        Response response = restApi.getAllProducts();
        response.then().log().all()
                .statusCode(HttpStatus.SC_OK)
                //hamcraster
                .body("products[0].id", is(1))
                .body("products[0].title", is("Essence Mascara Lash Princess"))
                .body("products[0].price", is(9.99F));

        products = response.getBody().as(Products.class);
        utils.validateProducts(products);
    }

    //TODO

    @DisplayName("Insert a new product in application")
    @Test
    public void shouldAddProduct() {
        Product product = Product.builder()
                .title("BMW Pencil")
                .build();

        Response response = restApi.addProduct(product);
        response.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", is(195))
                .body("title", is("BMW Pencil"));
    }

    @Test
    public void shouldGetProductId() {
        Response response = restApi.getProductId(5);
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(5))
                .body("title", is("Red Nail Polish"));
    }


}
