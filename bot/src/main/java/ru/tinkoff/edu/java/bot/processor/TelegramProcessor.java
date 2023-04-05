package ru.tinkoff.edu.java.bot.processor;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.command.CommandBase;

import java.util.List;

@Component
public class TelegramProcessor extends BaseProcessor {
    public TelegramProcessor(List<CommandBase> commands) {
        super(commands);
    }

    private Pair<Interaction, Long> toInteraction(Update update) {
        if (update.message() != null)
            return Pair.of(Interaction.builder().content(update.message().text()).build(), update.message().chat().id());
        return Pair.of(Interaction.builder().build(), 0L); // unknown interaction
    }

    private BaseRequest<?, ?> fromInteraction(long chatId, Interaction interaction) {
        if (interaction != null)
            return new SendMessage(chatId, interaction.content());
        return fromInteraction(chatId, BaseProcessor.DEFAULT_INTERACTION);
    }

    public BaseRequest<?, ?> processUpdate(Update update) {
        var parsed = toInteraction(update);
        return fromInteraction(parsed.getRight(), process(parsed.getLeft()));
    }
}
