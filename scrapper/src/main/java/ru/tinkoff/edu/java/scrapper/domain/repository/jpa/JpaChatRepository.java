package ru.tinkoff.edu.java.scrapper.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.ChatEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.ChatRepository;

@Repository
public interface JpaChatRepository extends JpaRepository<ChatEntity, Long>, ChatRepository {
    @Override
    @Modifying
    @Query(value = "insert into chat (id) values (:id) on conflict do nothing", nativeQuery = true)
    void add(@Param("id") long id);

    @Override
    @Modifying
    @Query("delete from chat c where c.id=:id")
    int remove(@Param("id") long id);
}
