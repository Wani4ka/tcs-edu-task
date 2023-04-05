package ru.tinkoff.edu.java.scrapper.configuration;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "github")
@Configuration
public class GitHubProperties {
    @NotBlank
    private String baseUrl = "https://api.github.com";
}
