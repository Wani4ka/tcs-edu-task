package ru.tinkoff.edu.java.bot.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueueProperties {
    private String name;
    private String routingKey;
}
