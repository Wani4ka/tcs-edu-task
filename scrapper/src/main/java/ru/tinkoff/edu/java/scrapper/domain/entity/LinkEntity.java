package ru.tinkoff.edu.java.scrapper.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.Hibernate;
import org.jetbrains.annotations.Nullable;
import org.jooq.Record;
import org.jooq.RecordMapper;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "link")
public class LinkEntity {

    public static final Mapper MAPPER = new Mapper();

    @Id
    @GeneratedValue
    private Long id;
    private URI url;
    @Column(name = "last_check", columnDefinition = "TIMESTAMP")
    private OffsetDateTime lastCheck;
    @Column(name = "last_event", columnDefinition = "TIMESTAMP")
    private OffsetDateTime lastEvent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LinkEntity that = (LinkEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    private static class Mapper implements RecordMapper<Record, LinkEntity> {

        @Override
        public @Nullable LinkEntity map(Record record) {
            if (record == null)
                return null;
            System.err.println(record.get("url", String.class));
            return new LinkEntity(
                    record.get("id", Long.class),
                    URI.create(record.get("url", String.class)),
                    record.get("last_check", OffsetDateTime.class),
                    record.get("last_event", OffsetDateTime.class)
            );
        }
    }
}
