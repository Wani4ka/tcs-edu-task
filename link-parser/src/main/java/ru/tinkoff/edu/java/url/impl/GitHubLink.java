package ru.tinkoff.edu.java.url.impl;

import ru.tinkoff.edu.java.url.Link;
import ru.tinkoff.edu.java.url.PatternLinkMatcher;

import java.util.regex.Pattern;

public record GitHubLink(String user, String repo) implements Link {
    @Override
    public String getAsString() {
        return String.format("https://github.com/%s/%s/", user, repo);
    }

    public static class Matcher extends PatternLinkMatcher {
        private static final Pattern pattern = Pattern.compile("https://github.com/([a-zA-Z0-9_\\-]+)/([a-zA-Z0-9_\\-]+)(?:/.*)?");

        public Matcher() {
            super(pattern);
        }

        @Override
        public Link parseMatchedLink(java.util.regex.Matcher matcher) {
            return new GitHubLink(matcher.group(1), matcher.group(2));
        }
    }
}
