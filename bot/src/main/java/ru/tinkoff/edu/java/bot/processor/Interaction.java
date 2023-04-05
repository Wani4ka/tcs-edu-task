package ru.tinkoff.edu.java.bot.processor;

import lombok.Builder;

@Builder
public record Interaction(
        String content
) {}
