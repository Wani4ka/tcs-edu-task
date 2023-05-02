package ru.tinkoff.edu.java.url.link.matcher;

import ru.tinkoff.edu.java.url.PatternLinkMatcher;
import ru.tinkoff.edu.java.url.link.Link;
import ru.tinkoff.edu.java.url.link.StackOverflowLink;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackOverflowLinkMatcher extends PatternLinkMatcher {
    private static final Pattern PATTERN = Pattern.compile("https://stackoverflow.com/questions/(\\d+)(?:/.*)?");

    public StackOverflowLinkMatcher() {
        super(PATTERN);
    }

    @Override
    public Link parseMatchedLink(Matcher matcher) {
        return new StackOverflowLink(matcher.group(1));
    }
}
