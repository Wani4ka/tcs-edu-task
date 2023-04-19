package ru.tinkoff.edu.java.bot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.processor.Interaction;

import java.net.URI;

@Component
@Slf4j
public class UnTrackCommand extends CommandBase {

    private final ScrapperClient client;

    public UnTrackCommand(ScrapperClient client) {
        super("/untrack", "Stop tracking the link");
        this.client = client;
    }

    @Override
    public Interaction handle(Interaction input) {
        log.info("User tried to untrack a link");
        var link = input.content().split(" ")[1];
        client.deleteLink(input.chatId(), new RemoveLinkRequest(URI.create(link)));
        return Interaction.builder(input.chatId())
                .content("The link was successfully removed from tracking!")
                .build();
    }

    @Override
    public boolean test(Interaction input) {
        return input.content().startsWith(name + " ");
    }
}
