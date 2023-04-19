package ru.tinkoff.edu.java.scrapper.dto.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record StackOverflowQuestionResponse(
        @JsonProperty("last_activity_date")
        OffsetDateTime lastActivityDate
) {
}
