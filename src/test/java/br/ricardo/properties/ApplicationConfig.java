package br.ricardo.properties;

import br.ricardo.context.SpringContextInit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "application")
public class ApplicationConfig {
    private String urlBase;

    private String LOGIN = "/auth/login";
    private String GET_CURRENT_USER = "/auth/me";
    private String GET_ALL_PRODUCTS = "/products";
    private String ADD_PRODUCT = "/products/add";
    private String GET_SINGLE_PRODUCT = "/products/{idProduct}";

}
