package org.skyhigh.afishadevappgui.service.logic.search;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.service.ServiceUtils;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;
import org.skyhigh.afishadevappgui.data.repository.DbUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class DbUserSearchServiceImpl implements DbUserSearchService {
    private final DbUserRepository dbUserRepository;

    @Override
    public List<DbUser> searchDbUsers(Integer userId, UUID authorId, String login) throws CommonFlkException {
        List<Object> args = new ArrayList<>();
        args.add(userId);
        args.add(authorId);
        args.add(login);
        if (ServiceUtils.countNotNull(args) > 1)
            return null;
        if (userId != null) {
            ArrayList<DbUser> users = new ArrayList<>();
            users.add(dbUserRepository.getDbUserById(userId));
            return users;
        }
        if (authorId != null) {
            ArrayList<DbUser> users = new ArrayList<>();
            users.add(dbUserRepository.getDbUserByAuthorId(authorId));
            return users;
        }
        if (login != null && !login.isEmpty()) {
            ArrayList<DbUser> users = new ArrayList<>();
            users.add(dbUserRepository.getDbUserByLogin(login));
            return users;
        }
        return dbUserRepository.getAllDbUsers(SortDirection.NONE, null);
    }
}
