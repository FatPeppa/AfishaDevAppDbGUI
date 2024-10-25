package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Author;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;
import org.skyhigh.afishadevappgui.data.validation.args.Flk10010000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000003Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000015Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class DbUserDAOImpl extends BaseTable implements DbUserDAO {
    /**
     * Конструктор для DAO с использованием имени таблицы по умолчанию ("db_user")
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public DbUserDAOImpl(@NonNull final DbConnector dbConnector) throws SQLException {
        super("db_user", dbConnector);
    }

    /**
     * Конструктор для DAO с использованием передаваемого имени таблицы
     * @param tableName Имя таблицы
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public DbUserDAOImpl(String tableName, DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    @Override
    public int saveDbUser(@NonNull DbUser dbUser) throws SQLException, CommonFlkException {
        Author savingAuthor = new Author(
                null,
                dbUser.getUserLogin()
        );
        Flk10000003Validator.validate(savingAuthor);
        AuthorDAO authorDAO = new AuthorDAOImpl(dbConnector);
        UUID savedAuthorId = authorDAO.saveAuthor(
                savingAuthor
        );
        dbUser.setAuthorId(savedAuthorId);
        Flk10000015Validator.validate(dbUser);
        PreparedStatement psForUserSave = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (nextval('db_user_id'), ?, ?, ?)");
        psForUserSave.setObject(1, savedAuthorId);
        psForUserSave.setString(2, dbUser.getUserLogin());
        psForUserSave.setString(3, dbUser.getUserPass());
        int stRes = super.executeSqlStatementUpdate(psForUserSave);
        log.log(Level.INFO, "database logging: " + stRes + " rows inserted into " + super.getTableName());
        List<DbUser> dbUsers = getAllDbUsers(SortDirection.NONE, null);
        for (DbUser du : dbUsers)
            if (du.getUserLogin().equals(dbUser.getUserLogin()))
                return du.getUserId();
        return -1;
    }

    @Override
    public void updateDbUserPassById(int userId, @NonNull String pass) throws SQLException, CommonFlkException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.user_pass = ?1 WHERE t.user_id = ?2"
        );
        ps.setString(1, pass);
        ps.setInt(2, userId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void deleteDbUserById(int userId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " t WHERE t.user_id = ?"
        );
        ps.setInt(1, userId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public DbUser getDbUserById(int userId) throws SQLException {
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.user_id, t.author_id, t.user_login, t.user_pass" +
                        " FROM " + super.getTableName() + " t WHERE t.user_id=?",
                SortDirection.NONE,
                null
        );
        ps.setInt(1, userId);
        return getSingleUser(ps);
    }

    @Override
    public DbUser getDbUserByLogin(@NonNull String login) throws SQLException {
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.user_id, t.author_id, t.user_login, t.user_pass" +
                        " FROM " + super.getTableName() + " t WHERE t.user_login=?",
                SortDirection.NONE,
                null
        );
        ps.setString(1, login);
        return getSingleUser(ps);
    }

    @Override
    public DbUser getDbUserByAuthorId(UUID authorId) throws SQLException {
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.user_id, t.author_id, t.user_login, t.user_pass" +
                        " FROM " + super.getTableName() + " t WHERE t.author_id=?",
                SortDirection.NONE,
                null
        );
        ps.setObject(1, authorId);
        return getSingleUser(ps);
    }

    @Override
    public List<DbUser> getAllDbUsers(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                DbUser.class.getName(),
                DbUserDAO.class.getName(),
                "getAllDbUsers",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.user_id, t.author_id, t.user_login, t.user_pass" +
                        " FROM " + super.getTableName() + " t",
                SortDirection.NONE,
                null
        );
        return getUsers(ps);
    }

    /**
     * Внутренний метод для получения одного пользователя по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Объект DbUser - пользователь приложения (первый из массива, если получен массив), или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    private DbUser getSingleUser(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        DbUser dbUser = null;
        if (rs.next()) {
            dbUser = new DbUser(
                    rs.getInt(1),
                    rs.getObject(2, UUID.class),
                    rs.getString(3),
                    rs.getString(4)
            );
        }

        rs.close();
        ps.close();
        super.close();
        return dbUser;
    }

    /**
     * Внутренний метод для получения списка пользователей по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Список найденных по ps объектов DbUser (если данные не были найдены, то список пуст)
     * @throws SQLException Ошибка при работе с БД
     */
    private List<DbUser> getUsers(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<DbUser> dbUsers = new ArrayList<>();
        while (rs.next()) {
            dbUsers.add(new DbUser(
                    rs.getInt(1),
                    rs.getObject(2, UUID.class),
                    rs.getString(3),
                    rs.getString(4)
            ));
        }

        rs.close();
        ps.close();
        super.close();
        return dbUsers;
    }
}
