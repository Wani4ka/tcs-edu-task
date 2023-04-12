package ru.tinkoff.edu.java.scrapper;

import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.DirectoryResourceAccessor;
import lombok.SneakyThrows;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
