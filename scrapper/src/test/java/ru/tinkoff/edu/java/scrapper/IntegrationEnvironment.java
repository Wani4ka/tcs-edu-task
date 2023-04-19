package ru.tinkoff.edu.java.scrapper;

import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.DirectoryResourceAccessor;
import lombok.SneakyThrows;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Testcontainers
public abstract class IntegrationEnvironment {
    static final PostgreSQLContainer<?> CONTAINER;

    static {
        CONTAINER = new PostgreSQLContainer<>("postgres:15");
        CONTAINER.start();
        applyMigrations();
    }

    @SneakyThrows
    private static void applyMigrations() {
        var root = new File("").getAbsoluteFile().toPath().getParent();
        var con = getConnection();
        var db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(con));
        var liquibase = new Liquibase("migrations/master.xml", new DirectoryResourceAccessor(root), db);
        liquibase.update();
    }

    protected static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONTAINER.getJdbcUrl(), CONTAINER.getUsername(), CONTAINER.getPassword());
    }

    @DynamicPropertySource
    protected static void jdbcProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", CONTAINER::getUsername);
        registry.add("spring.datasource.password", CONTAINER::getPassword);
        registry.add("spring.liquibase.enabled", () -> false);
    }
}
