package ru.tinkoff.edu.java.scrapper.configuration;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record Scheduler(
        @NotNull Duration interval,
        @NotNull Duration maxLinkAge
) {}
