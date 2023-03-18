package ru.tinkoff.edu.java.url.test;

import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.url.LinkParser;
import ru.tinkoff.edu.java.url.link.GitHubLink;
import ru.tinkoff.edu.java.url.link.StackOverflowLink;

import static org.junit.jupiter.api.Assertions.*;

public class LinkParserTest {
    @Test
    public void testGitHubLink() {
        var url = "https://github.com/sanyarnd/tinkoff-java-course-2022/";
        var link = LinkParser.parseLink(url);
        assertNotNull(link);
        assertInstanceOf(GitHubLink.class, link);
        var ghLink = (GitHubLink) link;
        assertEquals("sanyarnd", ghLink.user());
        assertEquals("tinkoff-java-course-2022", ghLink.repo());
        assertEquals(url, ghLink.getAsString());
    }

    @Test
    public void testStackOverflowLink() {
        var url = "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c";
        var link = LinkParser.parseLink(url);
        assertNotNull(link);
        assertInstanceOf(StackOverflowLink.class, link);
        var soLink = (StackOverflowLink) link;
        assertEquals("1642028", soLink.id());
        assertEquals("https://stackoverflow.com/questions/1642028", soLink.getAsString());
    }

    @Test
    public void testInvalidLink() {
        var url = "https://stackoverflow.com/search?q=unsupported%20link";
        var link = LinkParser.parseLink(url);
        assertNull(link);
    }
}
