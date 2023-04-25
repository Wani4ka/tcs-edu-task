package ru.tinkoff.edu.java.bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.dto.LinkUpdateRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScrapperQueueListener {

    private final LinkUpdateProcessor processor;

    @RabbitListener(queues = "${app.queue.name}")
    public void receive(LinkUpdateRequest request) {
        processor.process(request);
    }

    @RabbitListener(queues = "${app.queue.name}.dlq")
    public void processFailedMessage(Message message) {
        log.info("Received failed message: {}", message.toString());
    }
}
