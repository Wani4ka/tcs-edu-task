/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq.routines;


import javax.annotation.processing.Generated;

import org.jooq.impl.AbstractRoutine;

import ru.tinkoff.edu.java.scrapper.domain.jooq.Public;


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
public class ClearUnusedChats extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a new routine call instance
     */
    public ClearUnusedChats() {
        super("clear_unused_chats", Public.PUBLIC);
    }
}