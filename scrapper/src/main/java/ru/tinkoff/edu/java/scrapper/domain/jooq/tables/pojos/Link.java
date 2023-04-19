/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.17.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String url;
    private OffsetDateTime lastCheck;
    private OffsetDateTime lastEvent;

    public Link() {}

    public Link(Link value) {
        this.id = value.id;
        this.url = value.url;
        this.lastCheck = value.lastCheck;
        this.lastEvent = value.lastEvent;
    }

    @ConstructorProperties({ "id", "url", "lastCheck", "lastEvent" })
    public Link(
        @NotNull Long id,
        @NotNull String url,
        @NotNull OffsetDateTime lastCheck,
        @NotNull OffsetDateTime lastEvent
    ) {
        this.id = id;
        this.url = url;
        this.lastCheck = lastCheck;
        this.lastEvent = lastEvent;
    }

    /**
     * Getter for <code>public.link.id</code>.
     */
    @NotNull
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.link.id</code>.
     */
    public void setId(@NotNull Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>public.link.url</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public String getUrl() {
        return this.url;
    }

    /**
     * Setter for <code>public.link.url</code>.
     */
    public void setUrl(@NotNull String url) {
        this.url = url;
    }

    /**
     * Getter for <code>public.link.last_check</code>.
     */
    @NotNull
    public OffsetDateTime getLastCheck() {
        return this.lastCheck;
    }

    /**
     * Setter for <code>public.link.last_check</code>.
     */
    public void setLastCheck(@NotNull OffsetDateTime lastCheck) {
        this.lastCheck = lastCheck;
    }

    /**
     * Getter for <code>public.link.last_event</code>.
     */
    @NotNull
    public OffsetDateTime getLastEvent() {
        return this.lastEvent;
    }

    /**
     * Setter for <code>public.link.last_event</code>.
     */
    public void setLastEvent(@NotNull OffsetDateTime lastEvent) {
        this.lastEvent = lastEvent;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Link other = (Link) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.url == null) {
            if (other.url != null)
                return false;
        }
        else if (!this.url.equals(other.url))
            return false;
        if (this.lastCheck == null) {
            if (other.lastCheck != null)
                return false;
        }
        else if (!this.lastCheck.equals(other.lastCheck))
            return false;
        if (this.lastEvent == null) {
            if (other.lastEvent != null)
                return false;
        }
        else if (!this.lastEvent.equals(other.lastEvent))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.url == null) ? 0 : this.url.hashCode());
        result = prime * result + ((this.lastCheck == null) ? 0 : this.lastCheck.hashCode());
        result = prime * result + ((this.lastEvent == null) ? 0 : this.lastEvent.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Link (");

        sb.append(id);
        sb.append(", ").append(url);
        sb.append(", ").append(lastCheck);
        sb.append(", ").append(lastEvent);

        sb.append(")");
        return sb.toString();
    }
}
