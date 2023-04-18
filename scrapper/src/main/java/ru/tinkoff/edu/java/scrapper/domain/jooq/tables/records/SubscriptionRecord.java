/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records;


import java.beans.ConstructorProperties;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Subscription;


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
public class SubscriptionRecord extends UpdatableRecordImpl<SubscriptionRecord> implements Record3<Long, Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.subscription.id</code>.
     */
    public void setId(@NotNull Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.subscription.id</code>.
     */
    @NotNull
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.subscription.link_id</code>.
     */
    public void setLinkId(@Nullable Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.subscription.link_id</code>.
     */
    @Nullable
    public Long getLinkId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.subscription.chat_id</code>.
     */
    public void setChatId(@Nullable Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.subscription.chat_id</code>.
     */
    @Nullable
    public Long getChatId() {
        return (Long) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row3<Long, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row3<Long, Long, Long> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    @NotNull
    public Field<Long> field1() {
        return Subscription.SUBSCRIPTION.ID;
    }

    @Override
    @NotNull
    public Field<Long> field2() {
        return Subscription.SUBSCRIPTION.LINK_ID;
    }

    @Override
    @NotNull
    public Field<Long> field3() {
        return Subscription.SUBSCRIPTION.CHAT_ID;
    }

    @Override
    @NotNull
    public Long component1() {
        return getId();
    }

    @Override
    @Nullable
    public Long component2() {
        return getLinkId();
    }

    @Override
    @Nullable
    public Long component3() {
        return getChatId();
    }

    @Override
    @NotNull
    public Long value1() {
        return getId();
    }

    @Override
    @Nullable
    public Long value2() {
        return getLinkId();
    }

    @Override
    @Nullable
    public Long value3() {
        return getChatId();
    }

    @Override
    @NotNull
    public SubscriptionRecord value1(@NotNull Long value) {
        setId(value);
        return this;
    }

    @Override
    @NotNull
    public SubscriptionRecord value2(@Nullable Long value) {
        setLinkId(value);
        return this;
    }

    @Override
    @NotNull
    public SubscriptionRecord value3(@Nullable Long value) {
        setChatId(value);
        return this;
    }

    @Override
    @NotNull
    public SubscriptionRecord values(@NotNull Long value1, @Nullable Long value2, @Nullable Long value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SubscriptionRecord
     */
    public SubscriptionRecord() {
        super(Subscription.SUBSCRIPTION);
    }

    /**
     * Create a detached, initialised SubscriptionRecord
     */
    @ConstructorProperties({ "id", "linkId", "chatId" })
    public SubscriptionRecord(@NotNull Long id, @Nullable Long linkId, @Nullable Long chatId) {
        super(Subscription.SUBSCRIPTION);

        setId(id);
        setLinkId(linkId);
        setChatId(chatId);
    }

    /**
     * Create a detached, initialised SubscriptionRecord
     */
    public SubscriptionRecord(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.pojos.Subscription value) {
        super(Subscription.SUBSCRIPTION);

        if (value != null) {
            setId(value.getId());
            setLinkId(value.getLinkId());
            setChatId(value.getChatId());
        }
    }
}
