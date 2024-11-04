package org.skyhigh.afishadevappgui.service.logic.search;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface ProjectAuthorSearchService {
    /**
     * Метод поиска авторов проектов. Допустимые комбинации: authorId + projectId / authorId / projectId / all null
     * @param authorId ID автора
     * @param projectId ID проекта
     * @return Список искомых записей
     * @throws CommonFlkException Ошибка при выполнении проверок ФЛК
     */
    List<ProjectAuthor> searchProjectAuthor(UUID authorId, UUID projectId) throws CommonFlkException;
}
