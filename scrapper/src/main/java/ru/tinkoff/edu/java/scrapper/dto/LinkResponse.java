package ru.tinkoff.edu.java.scrapper.dto;

import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Validated
public record LinkResponse(
        @PositiveOrZero
        long id,
        URI url
) {
}
