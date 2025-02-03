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
    private String users;
    private String authLogin;
    private String currentProducts;
    private String allProducts;
    private String addProduct;
    private String singleProduct;

    private String expiredToken;

}
