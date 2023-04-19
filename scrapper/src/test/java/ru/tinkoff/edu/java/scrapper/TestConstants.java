package ru.tinkoff.edu.java.scrapper;

import java.net.URI;
import java.util.List;
import java.util.stream.LongStream;

public class TestConstants {
    public static final List<Long> IDS = LongStream.range(1, 11).boxed().toList();
    public static final List<URI> URIS = List.of(
            URI.create("https://stackoverflow.com/questions/8318911"),
            URI.create("https://stackoverflow.com/questions/245062"),
            URI.create("https://stackoverflow.com/questions/23569441"),
            URI.create("https://github.com/kelseyhightower/nocode"),
            URI.create("https://github.com/nipatiitti/condomjs"),
            URI.create("https://github.com/eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee/eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")
    );
}
