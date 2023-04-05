package ru.tinkoff.edu.java.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public record StackOverflowQuestionResponse(
        @JsonProperty("is_answered")
        boolean isAnswered,
        @JsonProperty("view_count")
        int viewCount,
        @JsonProperty("answer_count")
        int answerCount,
        @JsonProperty("last_activity_date")
        Instant lastActivityDate,
        @JsonProperty("creation_date")
        Instant creationDate,
        String title,
        String link,
        List<String> tags
) {
}
