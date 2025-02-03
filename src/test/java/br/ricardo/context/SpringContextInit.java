package br.ricardo.context;

import br.ricardo.Config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.hamcrest.Matchers.lessThan;

@EnableConfigurationProperties
@ActiveProfiles("qa")
@SpringBootTest(classes = Config.class)
public class SpringContextInit {

    @BeforeTestClass
    public static void setup(){

//        RestAssured.requestSpecification = new RequestSpecBuilder()
//                .setContentType("application/json")
//                //.addHeader("Accept", "application/json")
//                .addFilter(new RequestLoggingFilter()) //logs
//                .addFilter(new ResponseLoggingFilter()) // logs
//                .build();
//
//        RestAssured.responseSpecification = new ResponseSpecBuilder()
//                .expectContentType("application/json")
//                //.expectStatusCode(200)
//                .expectResponseTime(lessThan(2000L))
//                .build();
    }
}
