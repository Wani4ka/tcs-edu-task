package ru.tinkoff.edu.java.scrapper.domain.repository.jooq;

import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import ru.tinkoff.edu.java.scrapper.domain.repository.SubscriptionRepositoryTest;

@Nested
@SpringBootTest
public class JooqSubscriptionRepositoryTest extends SubscriptionRepositoryTest {
    @Autowired
    public JooqSubscriptionRepositoryTest(JooqChatRepository chatRepository, JooqLinkRepository linkRepository, JooqSubscriptionRepository subscriptionRepository, PlatformTransactionManager transactionManager) {
        super(chatRepository, linkRepository, subscriptionRepository, transactionManager);
    }
}
