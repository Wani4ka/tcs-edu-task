package ru.tinkoff.edu.java.bot.processor;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TelegramProcessorTest {

    @Autowired
    private TelegramProcessor processor;

    @Test
    public void testUnsupportedCommand() {
        var update = BotUtils.parseUpdate("""
                    {"message":{"text":"Hello bot!","chat":{"id":1033}}}
                """);
        var result = processor.processUpdate(update);
        assertNotNull(result);
        assertInstanceOf(SendMessage.class, result);
        assertEquals(result.getParameters().get("text"), BaseProcessor.DEFAULT_INTERACTION.content());
    }

}
