package ru.tinkoff.edu.java.bot.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.ListLinksResponse;
import ru.tinkoff.edu.java.bot.processor.Interaction;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListCommandTest {

    @Mock
    private ScrapperClient client;

    @InjectMocks
    private ListCommand command;

    @Test
    void testCommand() {
        when(client.listLinks(anyLong()))
                .thenReturn(new ListLinksResponse(List.of(new LinkResponse(1, URI.create("https://stackoverflow.com/questions/14415881"))), 1));

        final long chatId = 1033;
        var input = Interaction.builder(chatId).content("/list").build();
        var output = command.handle(input);
        assertNotNull(output);
        assertEquals(output.chatId(), chatId);
        assertFalse(output.content().isEmpty());
    }

}
