package ru.tinkoff.edu.java.scrapper.service.external;

import java.time.OffsetDateTime;
import ru.tinkoff.edu.java.url.link.Link;

public interface LinkUpdateChecker<T extends Link> {
    LinkUpdate checkForUpdates(T link, OffsetDateTime lastEvent);

    record LinkUpdate(OffsetDateTime time, String description) {}
}
