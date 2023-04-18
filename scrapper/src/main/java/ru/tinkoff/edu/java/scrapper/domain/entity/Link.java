package ru.tinkoff.edu.java.scrapper.domain.entity;

import lombok.*;

import java.net.URI;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    private long id;
    private URI url;
}
