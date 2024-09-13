package org.skyhigh.afishadevappgui.common.db;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс, предназначенный для подключения к БД (получения подключения)
 */
@AllArgsConstructor
public class DbConnector {
    /**
     * Поле, содержащее глобальные настройки приложения
     */
    private final ApplicationProperties properties;

    /**
     * Метод получения подключения к БД
     * @return Объект Connection - подключение к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.DB_URL(),
                properties.DB_USER(),
                properties.DB_PASSWORD()
        );
    }
}
