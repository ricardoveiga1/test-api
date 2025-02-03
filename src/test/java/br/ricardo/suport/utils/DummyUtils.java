package br.ricardo.suport.utils;

import br.ricardo.suport.apiClient.RestApi;
import br.ricardo.suport.domain.User;
import br.ricardo.suport.domain.products.Products;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import static io.restassured.RestAssured.given;

@Component
public class DummyUtils {
    @Autowired
    private RestApi restApi;

    public static String getTokenUtils(){
        User user = new User();
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .post("https://dummyjson.com/auth/login")
                .then().extract().path("accessToken");
    }

    public User getUserToken(User user) {
        Response response = restApi.appLogin(user);
        user.setToken(response.then().extract().jsonPath().getString("accessToken"));
        return user;
    }

    public void validateProducts(Products products) {
        //Validate Brand is commented because sometimes the brand is null

        products.getProducts().forEach(product -> {
            Assertions.assertTrue(product.getId() >= 0);
            Assertions.assertNotNull(product.getTitle());
            Assertions.assertNotNull(product.getDescription());
            Assertions.assertTrue(product.getPrice() >= 0);
            Assertions.assertTrue(product.getDiscountPercentage() >= 0);
            Assertions.assertTrue(product.getRating() >= 0);
            Assertions.assertTrue(product.getStock() >= 0);
//          Assertions.assertNotNull(product.getBrand());
            Assertions.assertNotNull(product.getCategory());
            Assertions.assertNotNull(product.getThumbnail());
            Assertions.assertNotNull(product.getTags());
            product.getTags().forEach(Assertions::assertNotNull);
            product.getReviews().forEach(Assertions::assertNotNull);
            Assertions.assertNotNull(product.getMeta());
        });
    }



}
