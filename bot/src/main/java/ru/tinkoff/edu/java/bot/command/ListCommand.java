package ru.tinkoff.edu.java.bot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.processor.Interaction;

import java.util.stream.Collectors;

@Component
@Slf4j
public class ListCommand extends CommandBase {

    private final ScrapperClient client;

    protected ListCommand(ScrapperClient client) {
        super("/list", "List the links to track");
        this.client = client;
    }

    @Override
    public Interaction handle(Interaction input) {
        log.info("User listed stored links");
        var links = client.listLinks(input.chatId()).links();
        return Interaction.builder(input.chatId())
                .content("Your links being watched:\n"
                    + links.stream().map(link -> link.url().toString()).collect(Collectors.joining("\n")))
                .build();
    }
}
