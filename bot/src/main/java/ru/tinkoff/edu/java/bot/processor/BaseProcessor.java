package ru.tinkoff.edu.java.bot.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.command.CommandBase;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component("processor")
public class BaseProcessor {
    public static final Interaction DEFAULT_INTERACTION = Interaction.builder().content("Unknown interaction!").build();
    private final List<CommandBase> commands;

    public Interaction process(Interaction input) {
        return commands.stream().filter(cmd -> cmd.test(input))
                .findFirst().map(cmd -> cmd.handle(input))
                .orElse(DEFAULT_INTERACTION);
    }

    public List<CommandBase> getCommands() {
        return Collections.unmodifiableList(commands);
    }
}
