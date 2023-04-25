package ru.tinkoff.edu.java.scrapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.configuration.QueueProperties;
import ru.tinkoff.edu.java.scrapper.dto.LinkUpdateRequest;

@Service
@RequiredArgsConstructor
public class ScrapperQueueProducer {

    private final RabbitTemplate rt;
    private final QueueProperties properties;

    public void send(LinkUpdateRequest update) {
        rt.convertAndSend(properties.getName(), properties.getRoutingKey(), update);
    }

}
