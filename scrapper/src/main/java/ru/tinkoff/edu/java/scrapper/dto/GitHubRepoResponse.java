package ru.tinkoff.edu.java.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record GitHubRepoResponse(
        int id,
        @JsonProperty("full_name")
        String fullName,
        @JsonProperty("updated_at")
        Date updatedAt,
        @JsonProperty("created_at")
        Date createdAt
) {
}
