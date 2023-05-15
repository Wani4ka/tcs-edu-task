package ru.tinkoff.edu.java.bot.processor;

import lombok.Builder;

@Builder(builderMethodName = "")
public record Interaction(
        long chatId,
        String content
) {
    public static InteractionBuilder builder() {
        return new InteractionBuilder();
    }

    public static InteractionBuilder builder(long chatId) {
        return builder().chatId(chatId);
    }
}
