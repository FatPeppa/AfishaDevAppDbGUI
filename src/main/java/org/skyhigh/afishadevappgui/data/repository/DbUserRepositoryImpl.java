package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.db.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.DbUserDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class DbUserRepositoryImpl implements DbUserRepository {
    private final DbUserDAO dbUserDAO;

    public DbUserRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            dbUserDAO = (new AfishaDevAppDb(applicationProperties)).getDbUserDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public int saveDbUser(@NonNull DbUser dbUser) throws CommonFlkException {
        try {
            return dbUserDAO.saveDbUser(dbUser);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateDbUserPassById(int userId, @NonNull String pass) throws CommonFlkException {
        try {
            dbUserDAO.updateDbUserPassById(userId, pass);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteDbUserById(int userId) throws CommonFlkException {
        try {
            dbUserDAO.deleteDbUserById(userId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public DbUser getDbUserById(int userId) throws CommonFlkException {
        try {
            return dbUserDAO.getDbUserById(userId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public DbUser getDbUserByLogin(@NonNull String login) throws CommonFlkException {
        try {
            return dbUserDAO.getDbUserByLogin(login);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public DbUser getDbUserByAuthorId(UUID authorId) throws CommonFlkException {
        try {
            return dbUserDAO.getDbUserByAuthorId(authorId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<DbUser> getAllDbUsers(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return dbUserDAO.getAllDbUsers(sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
