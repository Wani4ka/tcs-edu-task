package ru.tinkoff.edu.java.url.link;

public record GitHubLink(String user, String repo) implements Link {
    @Override
    public String getAsString() {
        return String.format("https://github.com/%s/%s/", user, repo);
    }
}
