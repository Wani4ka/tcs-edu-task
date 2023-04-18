package ru.tinkoff.edu.java.scrapper.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private long id;
    private long chatId;
    private long linkId;
}
