package ru.tinkoff.edu.java.scrapper.dto;

import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record ListLinksResponse(
        List<LinkResponse> links,
        @PositiveOrZero
        int size
) {
}
