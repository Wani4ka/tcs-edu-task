package ru.tinkoff.edu.java.scrapper.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tinkoff.edu.java.scrapper.domain.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;

import java.net.URI;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collection;

public interface JpaLinkRepository extends JpaRepository<LinkEntity, Long>, LinkRepository {
    @Override
    @Modifying
    @Query(value = "insert into link (url) values (:#{#url.toString()})", nativeQuery = true)
    void add(@Param("url") URI url);

    @Override
    @Modifying
    @Query("delete from link l where l.id=:id")
    int remove(long id);

    @Override
    @Modifying
    @Query("update link l set l.lastEvent=:lastEvent where l.id=:id")
    void updateLastEvent(@Param("id") long id, @Param("lastEvent") OffsetDateTime lastEvent);

    @Override
    @Query("select l from link l where l.id=:id")
    LinkEntity findById(@Param("id") long id);

    @Override
    @Query("select l from link l where l.url=:url")
    LinkEntity findByUrl(@Param("url") URI url);

    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "update link set last_check=?#{@reverse.apply(null)} where last_check < ?#{@reverse.apply(#maxAge)} returning id, url, last_check, last_event", nativeQuery = true)
    Collection<LinkEntity> peekOld(@Param("maxAge") Duration maxAge);
}
