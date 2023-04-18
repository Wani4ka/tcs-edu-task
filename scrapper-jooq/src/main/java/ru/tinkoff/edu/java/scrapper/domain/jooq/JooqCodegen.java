package ru.tinkoff.edu.java.scrapper.domain.jooq;

import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.DirectoryResourceAccessor;
import lombok.SneakyThrows;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.sql.DriverManager;

public class JooqCodegen {

    static final PostgreSQLContainer<?> CONTAINER;

    static {
        CONTAINER = new PostgreSQLContainer<>("postgres:15");
        CONTAINER.start();
        applyMigrations();
    }

    @SneakyThrows
    private static void applyMigrations() {
        var root = new File("").getAbsoluteFile().toPath();
        var con = DriverManager.getConnection(CONTAINER.getJdbcUrl(), CONTAINER.getUsername(), CONTAINER.getPassword());
        var db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(con));
        var liquibase = new Liquibase("migrations/master.xml", new DirectoryResourceAccessor(root), db);
        liquibase.update();
    }

    public static void main(String[] args) throws Exception {
        var jdbc = new Jdbc()
                .withDriver(CONTAINER.getDriverClassName())
                .withUrl(CONTAINER.getJdbcUrl())
                .withUsername(CONTAINER.getUsername())
                .withPassword(CONTAINER.getPassword());
        var database = new Database()
                .withName("org.jooq.meta.postgres.PostgresDatabase")
                .withInputSchema("public");

        var options = new Generate()
                .withGeneratedAnnotation(true)
                .withGeneratedAnnotationDate(false)
                .withNullableAnnotation(true)
                .withNullableAnnotationType("org.jetbrains.annotations.Nullable")
                .withNonnullAnnotation(true)
                .withNonnullAnnotationType("org.jetbrains.annotations.NotNull")
                .withJpaAnnotations(false)
                .withValidationAnnotations(true)
                .withSpringAnnotations(true)
                .withConstructorPropertiesAnnotation(true)
                .withConstructorPropertiesAnnotationOnPojos(true)
                .withConstructorPropertiesAnnotationOnRecords(true)
                .withFluentSetters(false)
                .withDaos(false)
                .withPojos(true);

        var target = new Target()
                .withPackageName("ru.tinkoff.edu.java.scrapper.domain.jooq")
                .withDirectory("scrapper/src/main/java");

        var configuration = new Configuration()
                .withJdbc(jdbc)
                .withGenerator(
                        new Generator()
                                .withDatabase(database)
                                .withGenerate(options)
                                .withTarget(target)
                );

        GenerationTool.generate(configuration);
    }
}
