package ru.tinkoff.edu.java.url;

import ru.tinkoff.edu.java.url.link.Link;
import ru.tinkoff.edu.java.url.link.matcher.LinkMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PatternLinkMatcher implements LinkMatcher {
    private final Pattern pattern;

    public PatternLinkMatcher(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public Link matchLink(String url) {
        Matcher matcher = pattern.matcher(url);
        return matcher.matches() ? parseMatchedLink(matcher) : null;
    }

    public abstract Link parseMatchedLink(Matcher matcher);
}
