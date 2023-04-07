package ru.tinkoff.edu.java.bot.processor;

import lombok.Builder;

@Builder
public record Interaction(
        long chatId,
        String content
) {}
