package ru.tinkoff.edu.java.bot.processor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.bot.command.ListCommand;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BaseProcessorTest {

    @Autowired
    private BaseProcessor processor;

    @Autowired
    private ListCommand listCommand;

    @Test
    public void testUnsupportedCommand() {
        final long chatId = 1033;
        var input = Interaction.builder().content("Hello bot!").chatId(chatId).build();
        var output = processor.process(input);
        assertNotNull(output);
        assertEquals(output.chatId(), chatId);
        assertEquals(output.content(), BaseProcessor.DEFAULT_INTERACTION.content());
    }

    @Test
    public void testLinksCommand() {
        final long chatId = 1033;
        var input = Interaction.builder().content("/list").chatId(chatId).build();
        assertTrue(listCommand.test(input));
        var output = processor.process(input);
        assertNotNull(output);
        assertEquals(output.chatId(), chatId);
        assertEquals(output.content(), "I don't remember which links I should track :(");
    }
}
