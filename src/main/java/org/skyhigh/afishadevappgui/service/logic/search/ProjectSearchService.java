package org.skyhigh.afishadevappgui.service.logic.search;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface ProjectSearchService {
    /**
     * Метод поиска проектов. Допустимые комбинации: projectId / projectName / loadDateDiapasonStart || loadDateDiapasonEnd / lastChangeDateDiapasonStart || lastChangeDateDiapasonEnd / versionNumber / all null
     * @param projectId ID проекта
     * @param projectName Наименование проекта
     * @param versionNumber Номер версии
     * @param loadDateDiapasonStart Начало диапазона поиска по датам загрузки
     * @param loadDateDiapasonEnd Окончание диапазона поиска по датам загрузки
     * @param lastChangeDateDiapasonStart Начало диапазона поиска по датам последнего внесения изменений
     * @param lastChangeDateDiapasonEnd Окончание диапазона поиска по датам последнего внесения изменений
     * @return Список искомых записей
     * @throws CommonFlkException Ошибка при выполнении проверок ФЛК
     */
    List<Project> searchProject(
            UUID projectId,
            String projectName,
            String versionNumber,
            LocalDateTime loadDateDiapasonStart,
            LocalDateTime loadDateDiapasonEnd,
            LocalDateTime lastChangeDateDiapasonStart,
            LocalDateTime lastChangeDateDiapasonEnd
    ) throws CommonFlkException;
}
