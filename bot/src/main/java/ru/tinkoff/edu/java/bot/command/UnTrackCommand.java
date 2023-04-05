package ru.tinkoff.edu.java.bot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.processor.Interaction;

@Component
@Slf4j
public class UnTrackCommand extends CommandBase {
    public UnTrackCommand() {
        super("/untrack", "Stop tracking the link");
    }

    @Override
    public Interaction handle(Interaction input) {
        log.info("User tried to untrack a link");
        return Interaction.builder().content("If I had a database, I would remove your link from there!").build();
    }
}
