package ru.tinkoff.edu.java.scrapper;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
@OpenAPIDefinition(
        info = @Info(
                title = "Scrapper API",
                version = "1.0.0",
                contact = @Contact(name = "Ivan Lutsenka", url = "https://github.com/Wani4ka")
        )
)
public class ScrapperApplication {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(ScrapperApplication.class, args);
        ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
        System.out.println(config);
    }
}
