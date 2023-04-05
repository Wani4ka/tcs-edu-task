package ru.tinkoff.edu.java.bot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.processor.Interaction;

@Component
@Slf4j
public class ListCommand extends CommandBase {
    protected ListCommand() {
        super("/list", "List the links to track");
    }

    @Override
    public Interaction handle(Interaction input) {
        log.info("User listed stored links");
        return Interaction.builder().content("I don't remember which links I should track :(").build();
    }
}
