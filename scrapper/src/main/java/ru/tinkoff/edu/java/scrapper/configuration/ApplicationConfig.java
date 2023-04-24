package ru.tinkoff.edu.java.scrapper.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.scrapper.configuration.access.AccessType;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
        @NotNull GitHubProperties github,
        @NotNull StackOverflowProperties stackoverflow,
        @NotNull BotProperties bot,
        @NotNull Scheduler scheduler,
        @NotNull AccessType databaseAccessType
) {
}
