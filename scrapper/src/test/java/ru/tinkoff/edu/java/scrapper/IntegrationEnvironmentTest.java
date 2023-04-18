package ru.tinkoff.edu.java.scrapper;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationEnvironmentTest extends IntegrationEnvironment {

    private static final Set<String> expectedTableNames = Set.of("chat", "databasechangelog", "databasechangeloglock", "link", "subscription");

    @SneakyThrows
    @Test
    void testTableNames() {
        Set<String> names = new HashSet<>();
        try (
                var con = getConnection();
                var rs = con.getMetaData().getTables(null, "public", "%", new String[]{"TABLE"})
        ) {
            while (rs.next()) {
                names.add(rs.getString("TABLE_NAME"));
            }
        }
        assertAll(names.stream().map(name -> () -> assertTrue(expectedTableNames.contains(name))));
        assertAll(expectedTableNames.stream().map(name -> () -> assertTrue(names.contains(name))));
    }
}
