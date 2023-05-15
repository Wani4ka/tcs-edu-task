package ru.tinkoff.edu.java.bot.processor;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.command.CommandBase;

import java.util.List;

@Component
public class TelegramProcessor extends BaseProcessor {
    public TelegramProcessor(List<CommandBase> commands) {
        super(commands);
    }

    private Interaction toInteraction(Update update) {
        if (update.message() != null) {
            return Interaction.builder(update.message().chat().id())
                .content(update.message().text())
                .build();
        }
        return Interaction.builder().build(); // unknown interaction
    }

    private BaseRequest<?, ?> fromInteraction(Interaction interaction) {
        if (interaction != null) {
            return new SendMessage(interaction.chatId(), interaction.content());
        }
        return fromInteraction(BaseProcessor.getDefaultInteraction(0));
    }

    public BaseRequest<?, ?> processUpdate(Update update) {
        var parsed = toInteraction(update);
        return fromInteraction(process(parsed));
    }
}
