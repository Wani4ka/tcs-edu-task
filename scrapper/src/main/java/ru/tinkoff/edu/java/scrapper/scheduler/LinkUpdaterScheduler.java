package ru.tinkoff.edu.java.scrapper.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.service.LinkUpdater;

@Slf4j
@Component
@RequiredArgsConstructor
public class LinkUpdaterScheduler {
    private final LinkUpdater updater;

    @Scheduled(fixedDelayString = "#{scheduler.interval}")
    public void update() {
        int updated = updater.update();
        if (updated > 0) {
            log.info("{} link(s) updated", updated);
        }
    }
}
