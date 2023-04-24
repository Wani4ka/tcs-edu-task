package ru.tinkoff.edu.java.scrapper;

import java.net.URI;
import java.util.List;
import java.util.stream.LongStream;

public class TestConstants {
    public static final List<Long> IDS = LongStream.range(1, 11).boxed().toList();
    public static final List<URI> URIS = List.of(
            URI.create("https://stackoverflow.com/questions/8318911"), // Why does HTML think "chucknorris" is a color?
            URI.create("https://stackoverflow.com/questions/245062"), // What's the difference between JavaScript and Java?
            URI.create("https://stackoverflow.com/questions/14415881"), // How can I pair socks from the pile efficiently?
            URI.create("https://github.com/kelseyhightower/nocode"), // The best way to write secure and reliable applications. Write nothing; deploy nowhere.
            URI.create("https://github.com/nipatiitti/condomjs"), // Safe and thin wrapper for React when you need to use Penis.js
            URI.create("https://github.com/eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee/eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee") // eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
    );
}
