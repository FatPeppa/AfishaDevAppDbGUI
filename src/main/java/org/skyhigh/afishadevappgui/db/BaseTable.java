package org.skyhigh.afishadevappgui.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseTable implements AutoCloseable {
    Connection connection;  // JDBC-соединение для работы с таблицей
    String tableName;       // Имя таблицы
    DbConnector dbConnector;

    BaseTable(String tableName, DbConnector dbConnector) throws SQLException { // Для реальной таблицы передадим в конструктор её имя
        this.tableName = tableName;
        this.dbConnector = dbConnector;
        this.connection = dbConnector.getConnection();
    }

    // Закрытие
    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка закрытия SQL соединения!");
        }
    }

    // Выполнить SQL команду без параметров в СУБД, по завершению выдать сообщение в консоль
    void executeSqlStatement(String sql, String description) throws SQLException {
        reopenConnection(); // переоткрываем (если оно неактивно) соединение с СУБД
        Statement statement = connection.createStatement();  // Создаем statement для выполнения sql-команд
        statement.execute(sql); // Выполняем statement - sql команду
        statement.close();      // Закрываем statement для фиксации изменений в СУБД
        if (description != null)
            System.out.println(description);
    };

    void executeSqlStatement(String sql) throws SQLException {
        executeSqlStatement(sql, null);
    };


    // Активизация соединения с СУБД, если оно не активно.
    void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = dbConnector.getConnection();
        }
    }
}
