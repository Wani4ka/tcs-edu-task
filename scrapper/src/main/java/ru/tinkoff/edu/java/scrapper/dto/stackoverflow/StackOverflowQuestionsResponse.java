package ru.tinkoff.edu.java.scrapper.dto.stackoverflow;

import java.util.List;

public record StackOverflowQuestionsResponse(
        List<StackOverflowQuestionResponse> items
) {
}
