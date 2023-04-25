package ru.tinkoff.edu.java.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.dto.LinkUpdateRequest;

@Component
@RabbitListener(queues = "${app.queue.queue-name}")
@RequiredArgsConstructor
public class ScrapperQueueListener {

    private final LinkUpdateProcessor processor;

    @RabbitHandler
    public void receive(LinkUpdateRequest request) {
        processor.process(request);
    }
}
