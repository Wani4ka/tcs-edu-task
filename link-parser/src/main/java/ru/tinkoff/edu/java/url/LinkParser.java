package ru.tinkoff.edu.java.url;

import ru.tinkoff.edu.java.url.link.Link;
import ru.tinkoff.edu.java.url.link.matcher.GitHubLinkMatcher;
import ru.tinkoff.edu.java.url.link.matcher.LinkMatcher;
import ru.tinkoff.edu.java.url.link.matcher.StackOverflowLinkMatcher;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkParser {
    private static final Map<String, LinkMatcher> REGISTERED = new HashMap<>();

    public static void registerMatcher(String id, LinkMatcher parser) {
        REGISTERED.put(id, parser);
    }

    static { // register default
        registerMatcher("github", new GitHubLinkMatcher());
        registerMatcher("stackoverflow", new StackOverflowLinkMatcher());
    }

    public static Link parseLink(String url) {
        try {
            if (url == null || !url.equals(URLDecoder.decode(url, StandardCharsets.UTF_8))) {
                throw new IllegalArgumentException("Link is invalid");
            }
            return REGISTERED.values().stream()
                    .map(parser -> parser.matchLink(url))
                    .filter(Objects::nonNull)
                    .findFirst().orElseThrow();
        } catch (IllegalArgumentException | NoSuchElementException ignored) {
            return null;
        }
    }

    private LinkParser() {}
}
