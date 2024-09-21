package br.ricardo.tests;

import br.ricardo.Config;
import br.ricardo.context.SpringContextInit;
import br.ricardo.suport.apiClient.RestApi;
import br.ricardo.suport.dto.User;
import io.restassured.response.Response;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

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
        Response response =  restApi.loginAndGetToken();
        //response.getBody().as.(User.class);
        System.out.println(response.toString());

        //response.then().body("token", is (not()));
        //response.then().body("method", is("GET"));

    }

}
