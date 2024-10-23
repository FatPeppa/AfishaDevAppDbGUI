package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository {
    UUID saveAuthor(@NonNull Author author) throws CommonFlkException;
    void updateAuthorLoginById(@NonNull UUID authorId, @NonNull String authorLogin) throws CommonFlkException;
    void deleteAuthorById(@NonNull UUID authorId) throws CommonFlkException;
    Author getAuthorById(@NonNull UUID authorId) throws CommonFlkException;
    Author getAuthorByName(@NonNull String authorLogin) throws CommonFlkException;
    List<Author> getAllAuthors(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException;
}
