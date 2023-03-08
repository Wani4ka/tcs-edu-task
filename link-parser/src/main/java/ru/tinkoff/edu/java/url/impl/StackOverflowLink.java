package ru.tinkoff.edu.java.url.impl;

import ru.tinkoff.edu.java.url.Link;
import ru.tinkoff.edu.java.url.PatternLinkMatcher;

import java.util.regex.Pattern;

public record StackOverflowLink(String id) implements Link {

    @Override
    public String getAsString() {
        return String.format("https://stackoverflow.com/questions/%s", id);
    }

    public static class Matcher extends PatternLinkMatcher {
        private static final Pattern pattern = Pattern.compile("https://stackoverflow.com/questions/(\\d+)(?:/.*)?");

        public Matcher() {
            super(pattern);
        }

        @Override
        public Link parseMatchedLink(java.util.regex.Matcher matcher) {
            return new StackOverflowLink(matcher.group(1));
        }
    }
}
