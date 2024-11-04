package org.skyhigh.afishadevappgui.service.logic.search;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Author;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface AuthorSearchService {
    /**
     * Метод поиска авторов. Допустимые комбинации: authorId / authorLogin / all null
     * @param authorId ID автора
     * @param authorLogin Логин автора
     * @return Список искомых значений
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Author> searchAuthors(UUID authorId, String authorLogin) throws CommonFlkException;
}
