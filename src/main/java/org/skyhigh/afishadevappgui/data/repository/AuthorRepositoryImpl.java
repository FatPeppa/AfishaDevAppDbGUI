package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.db.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.AuthorDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.Author;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AuthorRepositoryImpl implements AuthorRepository {
    private final AuthorDAO authorDAO;

    public AuthorRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            authorDAO = (new AfishaDevAppDb(applicationProperties)).getAuthorDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public UUID saveAuthor(@NonNull Author author) throws CommonFlkException {
        try {
            return authorDAO.saveAuthor(author);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateAuthorLoginById(@NonNull UUID authorId, @NonNull String authorLogin) throws CommonFlkException {
        try {
            authorDAO.updateAuthorLoginById(authorId, authorLogin);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteAuthorById(@NonNull UUID authorId) throws CommonFlkException {
        try {
            authorDAO.deleteAuthorById(authorId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public Author getAuthorById(@NonNull UUID authorId) throws CommonFlkException {
        try {
            return authorDAO.getAuthorById(authorId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public Author getAuthorByName(@NonNull String authorLogin) throws CommonFlkException {
        try {
            return authorDAO.getAuthorByName(authorLogin);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Author> getAllAuthors(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return authorDAO.getAllAuthors(sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
