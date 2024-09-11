package org.skyhigh.afishadevappgui.db;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.properties.ApplicationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@AllArgsConstructor
public class DbConnector {
    private final ApplicationProperties properties;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.DB_URL(),
                properties.DB_USER(),
                properties.DB_PASSWORD()
        );
    }
}
