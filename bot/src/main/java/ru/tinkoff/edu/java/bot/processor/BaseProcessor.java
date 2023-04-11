package ru.tinkoff.edu.java.bot.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.command.CommandBase;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component("processor")
public class BaseProcessor {
    public static final String DEFAULT_INTERACTION_MESSAGE = "Unknown interaction!";
    private final List<CommandBase> commands;

    public static Interaction getDefaultInteraction(long chatId) {
        return Interaction.builder(chatId).content(DEFAULT_INTERACTION_MESSAGE).build();
    }

    public Interaction process(Interaction input) {
        return commands.stream().filter(cmd -> cmd.test(input))
                .findFirst().map(cmd -> cmd.handle(input))
                .orElse(getDefaultInteraction(input.chatId()));
    }

    public List<CommandBase> getCommands() {
        return Collections.unmodifiableList(commands);
    }
}
