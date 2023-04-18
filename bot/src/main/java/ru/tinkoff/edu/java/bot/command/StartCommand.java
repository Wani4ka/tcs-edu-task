package ru.tinkoff.edu.java.bot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.processor.Interaction;

@Component
@Slf4j
public class StartCommand extends CommandBase {

    private final ScrapperClient client;

    protected StartCommand(ScrapperClient client) {
        super("/start", "Register user");
        this.client = client;
    }

    @Override
    public Interaction handle(Interaction input) {
        client.registerChat(input.chatId());
        log.info("User was registered");
        return Interaction.builder(input.chatId())
                .content("You are successfully registered! Check out /help for more info.")
                .build();
    }
}
