package ru.tinkoff.edu.java.bot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.processor.Interaction;

@Component
@Slf4j
public class StartCommand extends CommandBase {
    protected StartCommand() {
        super("/start", "Register user");
    }

    @Override
    public Interaction handle(Interaction input) {
        log.info("User was registered");
        return Interaction.builder(input.chatId())
                .content("You are successfully registered! Check out /help for more info.")
                .build();
    }
}
