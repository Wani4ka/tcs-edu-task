package ru.tinkoff.edu.java.url.link.matcher;

import ru.tinkoff.edu.java.url.PatternLinkMatcher;
import ru.tinkoff.edu.java.url.link.GitHubLink;
import ru.tinkoff.edu.java.url.link.Link;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitHubLinkMatcher extends PatternLinkMatcher {
    private static final Pattern PATTERN = Pattern.compile("https://github.com/([a-zA-Z0-9_\\-]+)/([a-zA-Z0-9_\\-]+)(?:/.*)?");

    public GitHubLinkMatcher() {
        super(PATTERN);
    }

    @Override
    public Link parseMatchedLink(Matcher matcher) {
        return new GitHubLink(matcher.group(1), matcher.group(2));
    }
}
