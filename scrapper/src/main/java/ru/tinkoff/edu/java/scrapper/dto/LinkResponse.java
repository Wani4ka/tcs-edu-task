package ru.tinkoff.edu.java.scrapper.dto;

import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.URL;

public record LinkResponse(
        @PositiveOrZero
        long id,
        @URL
        String url
) {
}
