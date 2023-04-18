package ru.tinkoff.edu.java.bot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.AddLinkRequest;
import ru.tinkoff.edu.java.bot.processor.Interaction;
import ru.tinkoff.edu.java.url.LinkParser;

import java.net.URI;
import java.util.Objects;

@Component
@Slf4j
public class TrackCommand extends CommandBase {

    private final ScrapperClient client;

    protected TrackCommand(ScrapperClient client) {
        super("/track", "Save a new link to track");
        this.client = client;
    }

    @Override
    public Interaction handle(Interaction input) {
        log.info("User tried to track a new link");
        var link = input.content().split(" ")[1];
        String linkType;
        try {
            var parsed = Objects.requireNonNull(LinkParser.parseLink(link));
            linkType = parsed.getClass().getSimpleName();
        } catch (Exception e) {
            return Interaction.builder(input.chatId())
                    .content("Invalid link! " + e.getMessage())
                    .build();
        }
        client.addLink(input.chatId(), new AddLinkRequest(URI.create(link)));
        return Interaction.builder(input.chatId())
                .content("Your " + linkType + " was successfully saved to tracking!")
                .build();
    }

    @Override
    public boolean test(Interaction input) {
        return input.content().startsWith(name + " ");
    }
}
