package ru.tinkoff.edu.java.scrapper.dto.github;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.Getter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public enum GitHubEventType {
    COMMIT_COMMENT("A commit comment is created"),
    CREATE("A Git branch or tag is created"),
    DELETE("A Git branch or tag is deleted"),
    FORK("A user forks a repository"),
    GOLLUM("A wiki page is created or updated"),
    ISSUE_COMMENT("Activity related to an issue or pull request comment"),
    ISSUES("Activity related to an issue"),
    MEMBER("Activity related to repository collaborators"),
    PUBLIC("When a private repository is made public"),
    PULL_REQUEST("Activity related to pull requests"),
    PULL_REQUEST_REVIEW("Activity related to pull request reviews"),
    PULL_REQUEST_REVIEW_COMMENT("Activity related to pull request review comments in the pull request's unified diff"),
    PULL_REQUEST_REVIEW_THREAD(
        "Activity related to a comment thread on a pull request being marked as resolved or unresolved"),
    PUSH("One or more commits are pushed to a repository branch or tag"),
    RELEASE("Activity related to a release"),
    SPONSORSHIP("Activity related to a sponsorship listing"),
    WATCH("When someone stars a repository");

    private final String jsonKey;
    private final String description;

    GitHubEventType(String description) {
        this.description = description;
        this.jsonKey = Arrays.stream(toString().split("_"))
                .map(str -> "" + str.charAt(0) + str.substring(1).toLowerCase())
                .collect(Collectors.joining()) + "Event";
    }

    public static GitHubEventType byJsonKey(String key) {
        return Arrays.stream(values()).filter(v -> Objects.equals(key, v.getJsonKey())).findFirst().orElseThrow();
    }

    public static class Deserializer extends JsonDeserializer<GitHubEventType> {
        @Override
        public GitHubEventType deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
            return GitHubEventType.byJsonKey(parser.readValueAs(String.class));
        }
    }
}
