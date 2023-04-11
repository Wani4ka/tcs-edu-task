package ru.tinkoff.edu.java.bot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.processor.Interaction;

@Component
@Slf4j
public class TrackCommand extends CommandBase {
    protected TrackCommand() {
        super("/track", "Save a new link to track");
    }

    @Override
    public Interaction handle(Interaction input) {
        log.info("User tried to track a new link");
        return Interaction.builder(input.chatId())
                .content("If I had a database, I would save your link!")
                .build();
    }
}
