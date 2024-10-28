package org.skyhigh.afishadevappgui.common.db;

import org.skyhigh.afishadevappgui.common.sort.SortDirection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс базовой таблицы, использующийся в качестве родителя при реализации DataSource-классов
 */
public class BaseTable implements AutoCloseable {
    Connection connection;
    String tableName;
    protected DbConnector dbConnector;

    public BaseTable(String tableName, DbConnector dbConnector) throws SQLException { // Для реальной таблицы передадим в конструктор её имя
        this.tableName = tableName;
        this.dbConnector = dbConnector;
        this.connection = dbConnector.getConnection();
    }

    /**
     * Метод закрытия соединения
     * @throws SQLException Ошибка при работе с БД
     */
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed())
            connection.close();
    }

    /**
     * Метод для получения подготовленного к выполнению SQL-выражения на чтение данных с параметрами
     * @param sql SQL-выражение на чтение данных в String формате
     * @param sortDirection - Режим сортировки
     * @param sortBy - Наименование поля таблицы, по которому осуществляется сортировка
     * @return Подготовленное к выполнению SQL-выражение PreparedStatement
     * @throws SQLException Ошибка при работе с БД
     */
    protected PreparedStatement prepareReadStatement(String sql, SortDirection sortDirection, String sortBy) throws SQLException {
        reopenConnection();
        if (sortDirection != SortDirection.NONE)
            return connection.prepareStatement(
                    sql + " ORDER BY " + convertClassFieldToBdAttribute(sortBy) +
                    " " + sortDirection.toString()
            );
        return connection.prepareStatement(sql);
    }

    /**
     * Метод для получения подготовленного к выполнению SQL-выражения
     * @param sql SQL-выражение в String формате
     * @return Подготовленное к выполнению SQL-выражение PreparedStatement
     * @throws SQLException Ошибка при работе с БД
     */
    protected PreparedStatement prepareStatement(String sql) throws SQLException {
        reopenConnection();
        return connection.prepareStatement(sql);
    }

    /**
     * Метод получения имени целевой таблицы
     * @return Имя целевой таблицы типа String
     */
    protected String getTableName() {
        return tableName;
    }

    /**
     * Метод для выполнения выражений модификации данных
     * @param sqlStatement SQL-выражение для выполнения
     * @return (1) количество строк для SQL DML, или (2) 0, если выражение не вернуло никаких данных
     * @throws SQLException Ошибка при работе с БД
     */
    protected int executeSqlStatementUpdate(PreparedStatement sqlStatement) throws SQLException {
        reopenConnection();
        int result = sqlStatement.executeUpdate();
       // sqlStatement.ex
        sqlStatement.close();
        connection.close();
        return result;
    }

    /**
     * Метод для выполнения выражений чтения данных. !!!!!ВАЖНО НЕ ЗАБЫВАТЬ ЗАКРЫВАТЬ ResultSet, Statement и Connection ПОСЛЕ ПОЛУЧЕНИЯ РЕЗУЛЬТАТА ИЗ ResultSet
     * @param sqlStatement SQL-выражение для выполнения
     * @return Результат выполнения выражения в виде ResultSet, или null, в случае, если выражение не вернула никаких данных
     * @throws SQLException Ошибка при работе с БД
     */
    protected ResultSet executeSqlStatementRead(PreparedStatement sqlStatement) throws SQLException {
        reopenConnection();
        return sqlStatement.executeQuery();
    }

    /**
     * Метод для первичного открытия (если соединение не было открыто) или переоткрытия соединения с БД (если соединение было закрыто)
     * @throws SQLException Ошибка при работе с БД
     */
    private void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = dbConnector.getConnection();
        }
    }

    private String convertClassFieldToBdAttribute(String classField) {
        if (classField != null)
            return classField.replaceAll("([A-Z])", "_$1").toLowerCase();
        return null;
    }
}
