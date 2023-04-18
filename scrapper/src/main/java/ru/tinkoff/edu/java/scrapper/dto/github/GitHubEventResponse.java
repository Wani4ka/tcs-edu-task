package ru.tinkoff.edu.java.scrapper.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class GitHubEventResponse {

    private long id;
    @JsonDeserialize(using = GitHubEventType.Deserializer.class)
    private GitHubEventType type;
    @JsonProperty("created_at")
    private OffsetDateTime createdAt;
}
