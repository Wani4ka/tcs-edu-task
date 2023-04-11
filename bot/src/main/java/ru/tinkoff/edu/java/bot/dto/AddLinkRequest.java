package ru.tinkoff.edu.java.bot.dto;

import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Validated
public record AddLinkRequest(
        URI link
) {
}
