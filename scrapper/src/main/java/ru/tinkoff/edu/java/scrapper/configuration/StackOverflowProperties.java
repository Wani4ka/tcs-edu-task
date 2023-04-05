package ru.tinkoff.edu.java.scrapper.configuration;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class StackOverflowProperties {
    @NotBlank
    private String baseUrl = "https://api.stackexchange.com/2.3";
}
