package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Author;
import org.skyhigh.afishadevappgui.data.validation.args.Flk10010000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000003Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class AuthorDAOImpl extends BaseTable implements AuthorDAO {
    /**
     * Конструктор для DAO с использованием имени таблицы по умолчанию ("author")
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public AuthorDAOImpl(@NonNull final DbConnector dbConnector) throws SQLException {
        super("author", dbConnector);
    }

    /**
     * Конструктор для DAO с использованием передаваемого имени таблицы
     * @param tableName Имя таблицы
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public AuthorDAOImpl(String tableName, DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    @Override
    public UUID saveAuthor(@NonNull Author author) throws SQLException, CommonFlkException {
        Flk10000003Validator.validate(author);
        UUID authorId = UUID.randomUUID();
        PreparedStatement ps = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (?, ?)");
        ps.setObject(1, authorId);
        ps.setString(2, author.getLogin());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows inserted into " + super.getTableName());
        return authorId;
    }

    @Override
    public void updateAuthorLoginById(@NonNull UUID authorId, @NonNull String authorLogin) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET login = ? WHERE author_id = ?"
        );
        ps.setString(1, authorLogin);
        ps.setObject(2, authorId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void deleteAuthorById(@NonNull UUID authorId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " WHERE author_id = ?"
        );
        ps.setObject(1, authorId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public Author getAuthorById(@NonNull UUID authorId) throws SQLException {
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.author_id author_id, t.login login " +
                        " FROM " + super.getTableName() + " t WHERE t.author_id=?",
                SortDirection.NONE,
                null
        );
        ps.setObject(1, authorId);
        return getSingleAuthor(ps);
    }

    @Override
    public Author getAuthorByName(@NonNull String authorLogin) throws SQLException {
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.author_id author_id, t.login login " +
                        " FROM " + super.getTableName() + " t WHERE t.login=?",
                SortDirection.NONE,
                null
        );
        ps.setObject(1, authorLogin);
        return getSingleAuthor(ps);
    }

    @Override
    public List<Author> getAllAuthors(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Author.class.getName(),
                AuthorDAO.class.getName(),
                "getAllAuthors",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.author_id author_id, t.login login FROM " + super.getTableName() + " t ",
                sortDirection,
                sortBy
        );
        return getAuthors(ps);
    }

    /**
     * Внутренний метод для получения одного автора по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Объект Author - автор (первый из массива, если получен массив), или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    private Author getSingleAuthor(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        Author author = null;
        if (rs.next()) {
            author = new Author(
                    rs.getObject(1, UUID.class),
                    rs.getString(2)
            );
        }

        rs.close();
        ps.close();
        super.close();
        return author;
    }

    /**
     * Внутренний метод для получения списка авторов по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Список найденных по ps объектов Author (если данные не были найдены, то список пуст)
     * @throws SQLException Ошибка при работе с БД
     */
    private List<Author> getAuthors(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<Author> authors = new ArrayList<>();
        while (rs.next()) {
            authors.add(new Author(
                    rs.getObject(1, UUID.class),
                    rs.getString(2)
            ));
        }

        rs.close();
        ps.close();
        super.close();
        return authors;
    }
}
