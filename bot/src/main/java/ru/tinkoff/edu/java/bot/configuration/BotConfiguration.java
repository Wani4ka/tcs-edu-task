package ru.tinkoff.edu.java.bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfiguration {
    @Bean("telegramBot")
    public TelegramBot getTelegramBot(ApplicationConfig cfg) {
        return new TelegramBot(cfg.telegram().token());
    }
}
