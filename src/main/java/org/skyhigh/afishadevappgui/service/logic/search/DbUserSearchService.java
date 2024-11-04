package org.skyhigh.afishadevappgui.service.logic.search;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface DbUserSearchService {
    /**
     * Метод поиска пользователей. Допустимые комбинации:  userId / authorId / login / all null
     * @param userId ID пользователя
     * @param authorId ID автора
     * @param login Логин
     * @return Список искомых значений
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<DbUser> searchDbUsers(Integer userId, UUID authorId, String login) throws CommonFlkException;
}
