package br.ricardo.context;

import br.ricardo.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@EnableConfigurationProperties
@ActiveProfiles("test")
@SpringBootTest(classes = Config.class)
public class SpringContextInit {
}
