package ru.tinkoff.edu.java.scrapper.domain.repository.jpa;

import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import ru.tinkoff.edu.java.scrapper.domain.repository.SubscriptionRepositoryTest;

@Nested
@SpringBootTest
public class JpaSubscriptionRepositoryTest extends SubscriptionRepositoryTest {
    @Autowired
    public JpaSubscriptionRepositoryTest(JpaChatRepository chatRepository, JpaLinkRepository linkRepository, JpaSubscriptionRepository subscriptionRepository, PlatformTransactionManager transactionManager) {
        super(chatRepository, linkRepository, subscriptionRepository, transactionManager);
    }
}
