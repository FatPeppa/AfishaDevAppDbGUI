package org.skyhigh.afishadevappgui.service.logic.search;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface RequirementAuthorSearchService {
    /**
     * Метод поиска авторов требований. Допустимые комбинации: requirementId + authorId / requirementId / authorId / all null
     * @param authorId ID автора
     * @param requirementId ID требования
     * @return Список искомых записей
     * @throws CommonFlkException Ошибка при выполнении проверок ФЛК
     */
    List<RequirementAuthor> searchRequirementAuthor(UUID authorId, UUID requirementId) throws CommonFlkException;
}
