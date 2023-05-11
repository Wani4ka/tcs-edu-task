package ru.tinkoff.edu.java.commons.configuration;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class QueueProperties {
    @NotBlank
    private String name;
    @NotBlank
    private String routingKey;

    private String deadLetterExchangeName;
    private String deadLetterQueueName;

    private void updateDLNames() {
        deadLetterExchangeName = name + ".dlx";
        deadLetterQueueName = name + ".dlq";
    }

    public void setName(String name) {
        this.name = name;
        updateDLNames();
    }
}
