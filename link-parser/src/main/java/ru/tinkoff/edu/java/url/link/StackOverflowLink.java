package ru.tinkoff.edu.java.url.link;

public record StackOverflowLink(String id) implements Link {

    @Override
    public String getAsString() {
        return String.format("https://stackoverflow.com/questions/%s", id);
    }
}
