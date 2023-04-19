package ru.tinkoff.edu.java.scrapper.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkEntity {
    private long id;
    private URI url;
    private OffsetDateTime lastCheck;
    private OffsetDateTime lastEvent;
}
