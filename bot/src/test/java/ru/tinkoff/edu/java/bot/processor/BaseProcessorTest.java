package ru.tinkoff.edu.java.bot.processor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BaseProcessorTest {
    @Autowired
    private BaseProcessor processor;

    @Test
    public void testUnsupportedCommand() {
        final long chatId = 1033;
        var input = Interaction.builder().content("Hello bot!").chatId(chatId).build();
        var output = processor.process(input);
        assertNotNull(output);
        assertEquals(chatId, output.chatId());
        assertEquals(BaseProcessor.DEFAULT_INTERACTION_MESSAGE, output.content());
    }

    @Test
    public void testSupportedCommand() {
        final long chatId = 1033;
        var input = Interaction.builder().content("/help").chatId(chatId).build();
        var output = processor.process(input);
        assertNotNull(output);
        assertEquals(chatId, output.chatId());
    }
}
