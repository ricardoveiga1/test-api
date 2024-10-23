package br.ricardo.tests;

import br.ricardo.Config;
import br.ricardo.context.SpringContextInit;
import br.ricardo.suport.apiClient.RestApi;
import br.ricardo.suport.domain.Product;
import br.ricardo.suport.domain.Products;
import br.ricardo.suport.domain.User;
import br.ricardo.suport.domain.Users;
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

    @Autowired
    private RestApi restApi;
    private Users users;


    @DisplayName("Validaçäo do status da aplicaçäo")
    @Test
    public void shouldValidateApiTest() {
        Response response = restApi.getSetup();
        response.getBody().prettyPrint();
        response.then().time(lessThan(2000L));

        response.then().body("status", is("ok"));
        response.then().body("method", is("GET"));
    }

    @DisplayName("Retorno dos usuários")
    @Test
    public void shouldGetUsers() {
        Response response = restApi.getUser();
        users = response.getBody().as(Users.class);
        response.getBody().prettyPrint();

//        users.getUsers().forEach(user -> {
//            Assertions.assertNotNull(user.getUsername());
//            Assertions.assertNotNull(user.getPassword());
//        });
    }


    @Test
    public void shouldValidateLoginAndToken() {
        Response response = restApi.getToken();
        response.getBody().prettyPrint();
        response.getBody().as(User.class);
        response.then()
                .statusCode(HttpStatus.SC_OK)
                //contract test
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("loginToken.json"))
                .body("accessToken", is(notNullValue()))
                .body("id", is(1))
                .body("username", is("emilys"));

    }

    @Test
    public void shouldGetCurrentUser() {
        Response response = restApi.getCurrentUser();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(1))
                .body("firstName", is("Emily"))
                .body("role", is("admin"));
    }

    @Test
    public void shouldGetAllProducts() {

        Response response = restApi.getAllProducts();
        response.getBody().as(Product.class);
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("products[0].id", is(1))
                .body("products[0].title", is("Essence Mascara Lash Princess"))
                .body("products[0].price", is(9.99F));

        Product product = response.getBody().as(Product.class);

        System.out.println(product.toString());
        //implemente mais validaçoes
    }

    @Test
    public void shouldAddProduct() {

        Response response = restApi.addProduct();
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
