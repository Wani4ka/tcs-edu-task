package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import ru.tinkoff.edu.java.scrapper.domain.repository.SubscriptionRepositoryTest;

@Nested
@SpringBootTest
public class JdbcSubscriptionRepositoryTest extends SubscriptionRepositoryTest {
    @Autowired
    public JdbcSubscriptionRepositoryTest(JdbcChatRepository chatRepository, JdbcLinkRepository linkRepository, JdbcSubscriptionRepository subscriptionRepository, PlatformTransactionManager transactionManager) {
        super(chatRepository, linkRepository, subscriptionRepository, transactionManager);
    }
}
