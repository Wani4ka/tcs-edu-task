package ru.tinkoff.edu.java.bot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.chat.Telegram;
import ru.tinkoff.edu.java.bot.dto.LinkUpdateRequest;

@RestController
@RequiredArgsConstructor
public class BotController {

    private final Telegram telegram;

    @PostMapping(path = "/updates", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Отправить обновление")
    @ApiResponse(responseCode = "200", description = "Обновление сработало")
    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    public void sendUpdate(@Valid @RequestBody LinkUpdateRequest update) {
        var msg = String.format("Detected an update for link %s:\n%s", update.url(), update.description());
        update.tgChatIds().forEach(chatId -> telegram.sendMessage(chatId, msg));
    }
}
