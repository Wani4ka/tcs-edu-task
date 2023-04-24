package ru.tinkoff.edu.java.scrapper.domain.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.StringUtils;

import java.net.URI;

@Converter(autoApply = true)
public class UriPersistenceConverter implements AttributeConverter<URI, String> {
    @Override
    public String convertToDatabaseColumn(URI uri) {
        return uri == null ? null : uri.toString();
    }

    @Override
    public URI convertToEntityAttribute(String s) {
        return StringUtils.hasLength(s) ? URI.create(s.trim()) : null;
    }
}
