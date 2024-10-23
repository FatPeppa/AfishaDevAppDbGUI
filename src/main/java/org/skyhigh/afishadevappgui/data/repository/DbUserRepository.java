package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

import java.util.List;
import java.util.UUID;

public interface DbUserRepository {
    int saveDbUser(@NonNull DbUser dbUser) throws CommonFlkException;
    void updateDbUserPassById(int userId, @NonNull String pass) throws CommonFlkException;
    void deleteDbUserById(int userId) throws CommonFlkException;
    DbUser getDbUserById(int userId) throws CommonFlkException;
    DbUser getDbUserByLogin(@NonNull String login) throws CommonFlkException;
    DbUser getDbUserByAuthorId(UUID authorId) throws CommonFlkException;
    List<DbUser> getAllDbUsers(
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;
}
