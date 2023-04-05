package ru.tinkoff.edu.java.bot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.processor.BaseProcessor;
import ru.tinkoff.edu.java.bot.processor.Interaction;

import java.util.stream.Collectors;

@Component
@Slf4j
public class HelpCommand extends CommandBase {
    private final BaseProcessor processor;

    protected HelpCommand(@Lazy BaseProcessor processor) {
        super("/help", "Get information about this bot and commands");
        this.processor = processor;
    }

    @Override
    public Interaction handle(Interaction message) {
        log.info("User asked for help");
        String available = processor.getCommands().stream()
                .map(cmd -> String.format("%s - %s", cmd.name, cmd.description))
                .collect(Collectors.joining("\n"));
        return Interaction.builder()
                .content("Hi! I'm a test project from Tinkoff, made by Wani4ka. Here are the commands you can use:\n" + available)
                .build();
    }
}
