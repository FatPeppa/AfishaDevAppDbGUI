package org.skyhigh.afishadevappgui.service.logic.search;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface RequirementSearchService {
    /**
     * Метод поиска требований. Допустимые комбинации: requirementId / requirementTypeId / loadDateDiapasonStart || loadDateDiapasonEnd / loadDateDiapasonStart || loadDateDiapasonEnd / all null
     * @param requirementId ID требования
     * @param requirementTypeId ID типа требования
     * @param loadDateDiapasonStart Начало диапазона поиска по датам загрузки
     * @param loadDateDiapasonEnd Окончание диапазона поиска по датам загрузки
     * @param lastChangeDateDiapasonStart Начало диапазона поиска по датам последнего внесения изменений
     * @param lastChangeDateDiapasonEnd Окончание диапазона поиска по датам последнего внесения изменений
     * @return Список искомых записей
     * @throws CommonFlkException Ошибка при выполнении проверок ФЛК
     */
    List<Requirement> searchRequirement(
            UUID requirementId,
            UUID requirementTypeId,
            LocalDateTime loadDateDiapasonStart,
            LocalDateTime loadDateDiapasonEnd,
            LocalDateTime lastChangeDateDiapasonStart,
            LocalDateTime lastChangeDateDiapasonEnd
    ) throws CommonFlkException;
}
