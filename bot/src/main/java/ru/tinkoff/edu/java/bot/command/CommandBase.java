package ru.tinkoff.edu.java.bot.command;

import lombok.Getter;
import ru.tinkoff.edu.java.bot.processor.Interaction;

public abstract class CommandBase {
    @Getter
    protected final String name;
    @Getter
    protected final String description;

    protected CommandBase(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract Interaction handle(Interaction input);

    public boolean test(Interaction input) {
        return input.content().equals(name);
    }
}
