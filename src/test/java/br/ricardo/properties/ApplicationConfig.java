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
    private String SINGLE_VIDEO_GAMES = "/videogame/{videoGameId}";

}
