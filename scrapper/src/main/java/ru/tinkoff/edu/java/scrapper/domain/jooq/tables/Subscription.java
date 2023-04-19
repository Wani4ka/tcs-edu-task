/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import ru.tinkoff.edu.java.scrapper.domain.jooq.Keys;
import ru.tinkoff.edu.java.scrapper.domain.jooq.Public;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.SubscriptionRecord;


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
public class Subscription extends TableImpl<SubscriptionRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.subscription</code>
     */
    public static final Subscription SUBSCRIPTION = new Subscription();

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<SubscriptionRecord> getRecordType() {
        return SubscriptionRecord.class;
    }

    /**
     * The column <code>public.subscription.id</code>.
     */
    public final TableField<SubscriptionRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.subscription.link_id</code>.
     */
    public final TableField<SubscriptionRecord, Long> LINK_ID = createField(DSL.name("link_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.subscription.chat_id</code>.
     */
    public final TableField<SubscriptionRecord, Long> CHAT_ID = createField(DSL.name("chat_id"), SQLDataType.BIGINT, this, "");

    private Subscription(Name alias, Table<SubscriptionRecord> aliased) {
        this(alias, aliased, null);
    }

    private Subscription(Name alias, Table<SubscriptionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.subscription</code> table reference
     */
    public Subscription(String alias) {
        this(DSL.name(alias), SUBSCRIPTION);
    }

    /**
     * Create an aliased <code>public.subscription</code> table reference
     */
    public Subscription(Name alias) {
        this(alias, SUBSCRIPTION);
    }

    /**
     * Create a <code>public.subscription</code> table reference
     */
    public Subscription() {
        this(DSL.name("subscription"), null);
    }

    public <O extends Record> Subscription(Table<O> child, ForeignKey<O, SubscriptionRecord> key) {
        super(child, key, SUBSCRIPTION);
    }

    @Override
    @NotNull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @NotNull
    public Identity<SubscriptionRecord, Long> getIdentity() {
        return (Identity<SubscriptionRecord, Long>) super.getIdentity();
    }

    @Override
    @NotNull
    public UniqueKey<SubscriptionRecord> getPrimaryKey() {
        return Keys.SUBSCRIPTION_PKEY;
    }

    @Override
    @NotNull
    public List<UniqueKey<SubscriptionRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.UNIQUE_PAIR);
    }

    @Override
    @NotNull
    public List<ForeignKey<SubscriptionRecord, ?>> getReferences() {
        return Arrays.asList(Keys.SUBSCRIPTION__FK_URL, Keys.SUBSCRIPTION__FK_CHAT);
    }

    private transient Link _link;
    private transient Chat _chat;

    /**
     * Get the implicit join path to the <code>public.link</code> table.
     */
    public Link link() {
        if (_link == null)
            _link = new Link(this, Keys.SUBSCRIPTION__FK_URL);

        return _link;
    }

    /**
     * Get the implicit join path to the <code>public.chat</code> table.
     */
    public Chat chat() {
        if (_chat == null)
            _chat = new Chat(this, Keys.SUBSCRIPTION__FK_CHAT);

        return _chat;
    }

    @Override
    @NotNull
    public Subscription as(String alias) {
        return new Subscription(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public Subscription as(Name alias) {
        return new Subscription(alias, this);
    }

    @Override
    @NotNull
    public Subscription as(Table<?> alias) {
        return new Subscription(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Subscription rename(String name) {
        return new Subscription(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Subscription rename(Name name) {
        return new Subscription(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Subscription rename(Table<?> name) {
        return new Subscription(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row3<Long, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Long, ? super Long, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super Long, ? super Long, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
