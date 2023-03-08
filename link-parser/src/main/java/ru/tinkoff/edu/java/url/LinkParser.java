package ru.tinkoff.edu.java.url;

import ru.tinkoff.edu.java.url.impl.GitHubLink;
import ru.tinkoff.edu.java.url.impl.StackOverflowLink;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LinkParser {
    private static final Map<String, LinkMatcher> registered = new HashMap<>();

    public static void registerMatcher(String id, LinkMatcher parser) {
        registered.put(id, parser);
    }

    static { // register default
        registerMatcher("github", new GitHubLink.Matcher());
        registerMatcher("stackoverflow", new StackOverflowLink.Matcher());
    }

    public static Link parseLink(String url) {
        try {
            if (url == null || !url.equals(URLDecoder.decode(url, StandardCharsets.UTF_8))) {
                return null;
            }
        } catch (IllegalArgumentException ignored) {
            return null;
        }
        for (var parser : registered.values()) {
            var result = parser.matchLink(url);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
