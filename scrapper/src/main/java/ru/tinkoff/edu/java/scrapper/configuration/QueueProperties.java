package ru.tinkoff.edu.java.scrapper.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueueProperties {
    private String queueName;
    private String exchangeName;
    private String routingKey;
}
