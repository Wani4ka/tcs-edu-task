package ru.tinkoff.edu.java.url.link.matcher;

import ru.tinkoff.edu.java.url.link.Link;

public interface LinkMatcher {
    Link matchLink(String url);
}
